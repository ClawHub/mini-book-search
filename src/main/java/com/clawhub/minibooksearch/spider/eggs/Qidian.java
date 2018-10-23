package com.clawhub.minibooksearch.spider.eggs;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clawhub.minibooksearch.core.http.HttpGenerator;
import com.clawhub.minibooksearch.core.http.HttpResInfo;
import com.clawhub.minibooksearch.core.util.IDGenarator;
import com.clawhub.minibooksearch.core.util.TimeUtil;
import com.clawhub.minibooksearch.entity.BookInfo;
import com.clawhub.minibooksearch.entity.BookSource;
import com.clawhub.minibooksearch.entity.Chapter;
import com.clawhub.minibooksearch.mapper.BookInfoMapper;
import com.clawhub.minibooksearch.mapper.BookSourceMapper;
import com.clawhub.minibooksearch.mapper.ChapterMapper;
import com.clawhub.minibooksearch.spider.core.Egg;
import com.clawhub.minibooksearch.spider.core.EggResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.cookie.Cookie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <Description> 起点中文网<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2018-10-15 21:28<br>
 */
@Component
public class Qidian implements Egg {
    /**
     * The Logger.
     */
    private Logger logger = LoggerFactory.getLogger(Qidian.class);

    /**
     * The Book info mapper.
     */
    @Autowired
    private BookInfoMapper bookInfoMapper;

    /**
     * The Book source mapper.
     */
    @Autowired
    private BookSourceMapper bookSourceMapper;

    /**
     * The Chapter mapper.
     */
    @Autowired
    private ChapterMapper chapterMapper;

    /**
     * The Book duplicate removal redis key prefix.
     */
    @Value("${redis.key.prefix.book.duplicate.removal}")
    private String bookDuplicateRemovalRedisKeyPrefix;

    /**
     * 最多解析书籍条数
     */
    @Value("${parse.search.keyword.max.num}")
    private int maxNum;
    /**
     * The String redis template.
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 爬虫入口
     *
     * @param keyword 关键词
     */
    @Override
    @Transactional
    public EggResult touch(String keyword) {
        logger.info("Qidian touch. keyword:{}", keyword);
        searchKeyword(keyword);
        return null;
    }

    /**
     * 查询关键词
     *
     * @param keyword the keyword
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    private void searchKeyword(String keyword) {
        logger.info("查询关键词");
        //搜索请求
        HttpResInfo resInfo = null;
        try {
            resInfo = HttpGenerator.sendGet("https://www.qidian.com/search?kw=" + URLEncoder.encode(keyword, "UTF-8"), 6000, 6000, null, false);
        } catch (UnsupportedEncodingException e) {
            logger.info("UnsupportedEncodingException", e);
            return;
        }
        //请求成功
        if (resInfo.getSuccess()) {
            String html = resInfo.getResult();
            //解析查询到的第一页数据
            parseSearchKeyword(html);
        }

    }

    /**
     * 解析查询到的第一页数据
     *
     * @param html the html
     */
    private void parseSearchKeyword(String html) {
        logger.info("解析查询到的第一页数据");
        Document document = Jsoup.parse(html);
        Elements elements = document.select(".book-img-text li");
        int num = 0;
        for (Element element : elements) {
            //控制解析的数量
            num++;
            if (num > maxNum) {
                return;
            }
            //解析单条数据
            parseSingleBook(element);
        }
    }

    /**
     * 解析单条数据
     *
     * @param element the element
     */
    private void parseSingleBook(Element element) {
        logger.info("解析单条数据");
        //获取书名
        String name = element.select(".book-mid-info a").first().text();
        //获取作者
        String auther = element.select(".book-mid-info .author").select(".name, i").text();
        //redis去重key
        String checkKey = bookDuplicateRemovalRedisKeyPrefix + name.trim() + "-" + auther.trim();
        //bookId
        String bookId = stringRedisTemplate.opsForValue().get(checkKey);
        logger.info("bookId:{}", bookId);
        if (StringUtils.isBlank(bookId)) {
            //获取图片url
            String picUrl = element.select(".book-img-box img").first().attr("src");
            //分类
            String classify = element.select(".book-mid-info .author a:not(.name)").text();
            //状态
            String state = element.select(".book-mid-info .author span").text();
            //简介
            String remark = element.select(".book-mid-info .intro").text();
            //总字数
            String numberStr = element.select(".book-right-info .total span").first().text();
            int number;
            int indexOf = numberStr.indexOf("万");
            if (indexOf > -1) {
                numberStr = numberStr.substring(0, indexOf);
                number = (int) (Double.parseDouble(numberStr) * 10000);
            } else {
                number = Integer.parseInt(numberStr);
            }
            bookId = IDGenarator.getID();
            //书籍基本信息入库
            logger.info("书籍基本信息入库 name:{},auther:{}", name, auther);
            BookInfo bookinfo = new BookInfo();
            bookinfo.setAuther(auther);
            bookinfo.setClassify(classify);
            bookinfo.setId(bookId);
            bookinfo.setName(name);
            bookinfo.setNumber(number);
            bookinfo.setPicUrl(picUrl);
            bookinfo.setRemark(remark);
            bookinfo.setState(state);
            bookInfoMapper.insert(bookinfo);
            //去重
            stringRedisTemplate.opsForValue().set(checkKey, bookId);
        }
        //更新时间
        String updateTime = element.select(".book-mid-info .update span").first().text();
        //url
        String url = "https:" + element.select(".book-mid-info h4 a").attr("href");
        //dataBid
        String dataBid = element.select(".book-mid-info h4 a").attr("data-bid");
        //目录链接
        String catalogUrl = url + "#Catalog";


        //书籍源信息入库
        logger.info("书籍源信息入库 website:起点中文网");
        BookSource bookSource = new BookSource();
        bookSource.setBookId(bookId);
        bookSource.setCatalogUrl(catalogUrl);
        bookSource.setSourceId(dataBid);
        bookSource.setUpdateTime(updateTime);
        bookSource.setUrl(url);
        bookSource.setWebSite("起点中文网");
        bookSourceMapper.insert(bookSource);


        //获取章节
//        category(catalogUrl, dataBid);


    }

    @Override
    public List<Chapter> chapter(String catalogUrl, String sourceId) {
        List<Chapter> chapters = new ArrayList<>();
        //获取token
        String token = getToken(catalogUrl);

        logger.info("获取章节");
        //获取章节
        HttpResInfo catalogRes = HttpGenerator.sendGet("https://book.qidian.com/ajax/book/category?_csrfToken=" + token + "&bookId=" + sourceId, 6000, 6000, null, false);
        if (catalogRes.isSuccess()) {
            JSONObject body = JSONObject.parseObject(catalogRes.getResult());
            JSONObject data = body.getJSONObject("data");
            JSONArray vs = data.getJSONArray("vs");
            if (vs.size() > 0) {
                for (int i = 0; i < vs.size(); i++) {
                    JSONObject vsObj = vs.getJSONObject(i);
                    String vN = vsObj.getString("vN");
                    String vId = vsObj.getString("vId");
                    JSONArray cs = vsObj.getJSONArray("cs");
                    if (cs.size() > 0) {
                        for (int j = 0; j < cs.size(); j++) {
                            JSONObject csObj = cs.getJSONObject(j);
                            String cN = csObj.getString("cN");
                            String uT = csObj.getString("uT");
                            String cU = csObj.getString("cU");
                            String uuid = csObj.getString("uuid");
                            String cnt = csObj.getString("cnt");
                            //章节名称
                            String name = vN + " " + cN;
                            if ("VIP卷".equalsIgnoreCase(vN)) {
                                name = cN;
                            }
                            //章节链接
                            String url = "https://read.qidian.com/chapter/" + cU;
                            //首发时间 2008-08-02 09:18:37
                            long dateTime = TimeUtil.StringToMilli(uT, TimeUtil.BASIC_DATE_TIME);
                            //序列
                            long sort = Long.parseLong(vId + uuid);
                            //章节字数
                            String number = cnt;

                            //章节数据入库
                            logger.info("章节名称:{}", name);
                            Chapter chapter = new Chapter();
                            chapter.setDateTime(dateTime);
                            chapter.setId(IDGenarator.getID());
                            chapter.setName(name);
                            chapter.setNumber(Integer.valueOf(number));
                            chapter.setSort(sort);
                            chapter.setSourceId(sourceId);
                            chapter.setUrl(url);
                            chapters.add(chapter);
//                            chapterMapper.insert(chapter);
                        }

                    }
                }
            }
        }
        return chapters;
    }

    /**
     * Gets token.
     *
     * @param catalogUrl the catalog url
     * @return the token
     */
    private String getToken(String catalogUrl) {
        logger.info("Gets token.");
        //获取cookie
        HttpResInfo cookieRes = HttpGenerator.sendGetwithCookie(catalogUrl, 6000, 6000, null);
        if (cookieRes.getSuccess()) {
            List<Cookie> cookies = cookieRes.getCookies();
            for (Cookie cookie : cookies) {
                if ("_csrfToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return "";
    }

}
