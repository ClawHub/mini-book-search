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
import com.clawhub.minibooksearch.spider.core.AbstractEgg;
import org.apache.http.cookie.Cookie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <Description> 起点中文网<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2018-10-15 21:28<br>
 */
@Component("www.qidian.com")
public class Qidian extends AbstractEgg {

    @Override
    protected String getSearchUrl(String keyword) {
        String url = null;
        try {
//            if(keyword!=null){
//                if(keyword.contains("recommend")){
//                    String dataType = keyword.split("=")[1];
//                    String channel = keyword.split("=")[2];
//                    url = "https://www.qidian.com/rank/recom?style=1&page=1&dateType=" + dataType + "&chn=" + channel;
//                }else{
//                    url = "https://www.qidian.com/search?kw=" + URLEncoder.encode(keyword, "UTF-8");
//                }
//            }else{
            url = "https://www.qidian.com/search?kw=" + URLEncoder.encode(keyword, "UTF-8");
//            }
            logger.info("查询地址为 URL= " + url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }

//    @Override
//    protected Recommend getRecommend(Element element,String dataType, String channel){
//        String name = element.select(".book-mid-info a").first().text();
//        long creatTime = Long.parseLong(TimeUtil.currentDateTime());
//        return new Recommend(name,dataType,channel,creatTime);
//    }

    @Override
    protected Elements getBookList(Document document) {
        return document.select(".book-img-text li");
    }


    @Override
    protected BookInfo getBookInfo(Element element) {
        //获取书名
        String name = element.select(".book-mid-info a").first().text();
        //获取作者
        String author = element.select(".book-mid-info .author").select(".name, i").text();
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
        return new BookInfo(name, author, "", classify, state, "", number, remark, picUrl);

    }

    @Override
    protected BookSource getBookSource(Element element) {
        String webSite = "www.qidian.com";
        //dataBid
        String dataBid = element.select(".book-mid-info h4 a").attr("data-bid");
        //更新时间
        String updateTime = element.select(".book-mid-info .update span").first().text();
        //url
        String url = "https:" + element.select(".book-mid-info h4 a").attr("href");
        //目录链接
        String catalogUrl = url + "#Catalog";
        return new BookSource(dataBid, url, updateTime, catalogUrl, webSite);
    }


    @Override
    public Map<String, Chapter> chapter(String catalogUrl, String sourceId) {
        Map<String, Chapter> chapters = new HashMap<>();
        int num = 0;
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
                            long dateTime = TimeUtil.stringToMilli(uT, TimeUtil.BASIC_DATE_TIME);
                            //序列
                            long sort = Long.parseLong(vId + uuid);
                            //章节字数
                            String number = cnt;

                            //章节数据
                            logger.info("章节名称:{}", name);
                            Chapter chapter = new Chapter();
                            chapter.setDateTime(dateTime);
                            chapter.setId(IDGenarator.getID());
                            chapter.setName(name);
                            chapter.setNumber(Integer.valueOf(number));
                            chapter.setSort(sort);
                            chapter.setSourceId(sourceId);
                            chapter.setUrl(url);
                            //收集
                            chapters.put(String.valueOf(num), chapter);
                            num++;
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

    @Override
    public String read(String chapterUrl) {
        //请求
        HttpResInfo httpResInfo = HttpGenerator.sendGet(chapterUrl, 6000, 6000, null, false);
        if (httpResInfo.getSuccess()) {
            String html = httpResInfo.getResult();
            Document document = Jsoup.parse(html);
            Elements elements = document.select(".read-content").select(".j_readContent p");
            StringBuilder sb = new StringBuilder();
            for (Element element : elements) {
                sb.append(element.text())
                        .append("\n");
            }
            return sb.toString();
        }
        return null;
    }
}
