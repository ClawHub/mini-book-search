package com.clawhub.minibooksearch.spider.eggs;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clawhub.minibooksearch.core.http.HttpGenerator;
import com.clawhub.minibooksearch.core.http.HttpResInfo;
import com.clawhub.minibooksearch.core.util.IDGenarator;
import com.clawhub.minibooksearch.core.util.TimeUtil;
import com.clawhub.minibooksearch.entity.*;
import com.clawhub.minibooksearch.spider.core.AbstractEgg;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    /**
     * Gets token.
     *
     * @param catalogUrl the catalog url
     * @return the token
     */
    private String getToken(String catalogUrl) {
        logger.info("Gets token.");
        return "DrHUWzmgueGRiOCR6hWaof5qtCjLMpyk6EeNXMy6";
//        //获取cookie
//        HttpResInfo cookieRes = HttpGenerator.sendGetwithCookie(catalogUrl, 6000, 6000, null);
//        if (cookieRes.getSuccess()) {
//            List<Cookie> cookies = cookieRes.getCookies();
//            for (Cookie cookie : cookies) {
//                if ("_csrfToken".equals(cookie.getName())) {
//                    return cookie.getValue();
//                }
//            }
//        }
//        return "";
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

    /**
     * 同步爬取目录并获取卷
     *
     * @param catalogUrl 目录url
     * @param sourceId   书籍源ID
     * @return 卷列表
     */
    @Override
    public CatalogResult parseCatalog(String catalogUrl, String sourceId) {
        CatalogResult catalogResult = new CatalogResult();
        List<Volume> volumeList = new ArrayList<>();
        List<BookSourceVolume> bookSourceVolumeList = new ArrayList<>();
        //获取token
        String token = getToken(catalogUrl);

        //请求目录
        HttpResInfo catalogRes = HttpGenerator.sendGet("https://book.qidian.com/ajax/book/category?_csrfToken=" + token + "&bookId=" + sourceId, 6000, 6000, null, false);
        if (!catalogRes.isSuccess()) {
            logger.error("同步爬取目录并获取卷失败，sourceId:{},catalogUrl:{}", sourceId, catalogUrl);
            return catalogResult.error();
        }
        //解析目录结果
        JSONObject body = JSONObject.parseObject(catalogRes.getResult());
        if (body == null) {
            logger.error("解析目录失败，没有响应体，sourceId:{},catalogUrl:{}", sourceId, catalogUrl);
            return catalogResult.error();
        }
        JSONObject data = body.getJSONObject("data");
        if (data == null) {
            logger.error("解析目录失败，没有返回data字段，sourceId:{},catalogUrl:{}", sourceId, catalogUrl);
            return catalogResult.error();
        }
        //vs:卷
        JSONArray vs = data.getJSONArray("vs");
        if (vs == null || vs.size() <= 0) {
            logger.error("解析目录失败，没有卷信息，sourceId:{},catalogUrl:{}", sourceId, catalogUrl);
            return catalogResult.error();
        }
        //卷排序
        long sort = Long.parseLong(sourceId);
        Set<String> serialNumSet = new HashSet<>();
        //解析卷信息
        for (int i = 0; i < vs.size(); i++) {
            //每卷中章节列表
            List<Chapter> chapterList = new ArrayList<>();
            //卷与章节对应关系
            List<VolumeChapter> volumeChapterList = new ArrayList<>();
            //获取每一卷
            JSONObject vsObj = vs.getJSONObject(i);
            //卷名称：“第一卷 七玄门风云”或者"VIP卷"，"作品相关" 即分三种模式解析
            String vN = vsObj.getString("vN");
            if ("VIP卷".equalsIgnoreCase(vN)) {
                System.out.println("VIP卷解析:" + vN);
                //解析VIP卷
                parseVipVolume(serialNumSet, vsObj, sourceId, volumeList, bookSourceVolumeList, sort, chapterList, volumeChapterList);
            } else {
                //解析卷
                parseVolume(serialNumSet, vN, vsObj, chapterList, volumeChapterList, sort, volumeList, bookSourceVolumeList, sourceId);
                sort++;
            }
            //章节列表
            catalogResult.addChapterList(chapterList);
            //卷与章节对应关系
            catalogResult.addVolumeChapterList(volumeChapterList);
        }
        catalogResult.setSourceId(sourceId);
        catalogResult.addVolumeList(volumeList);
        catalogResult.addBookSourceVolumeList(bookSourceVolumeList);
        return catalogResult.result();
    }

    /**
     * Parse volume.
     *
     * @param serialNumSet
     * @param vN                   the v n
     * @param vsObj                the vs obj
     * @param chapterList          the chapter list
     * @param volumeChapterList    the volume chapter list
     * @param sort                 the sort
     * @param volumeList           the volume list
     * @param bookSourceVolumeList the book source volume list
     * @param sourceId             the source id
     */
    private void parseVolume(Set<String> serialNumSet, String vN, JSONObject vsObj, List<Chapter> chapterList, List<VolumeChapter> volumeChapterList, long sort, List<Volume> volumeList, List<BookSourceVolume> bookSourceVolumeList, String sourceId) {
        //单个卷信息
        Volume volume = new Volume();
        //卷ID
        String vId = vsObj.getString("vId");
        volume.setId(vId);
        volume.setSort(sort);
        //章节数量
        int cCnt = vsObj.getIntValue("cCnt");
        volume.setChapterCount(cCnt);
        //卷字数
        int wC = vsObj.getIntValue("wC");
        volume.setWordCount(wC);
        //空格分隔
        String[] vNs = vN.split("　+|\\s+");
        if (vNs.length == 2) {
            System.out.println("正常卷解析:" + vN);
            //第一卷 七玄门风云
            volume.setSerialNum(vNs[0]);
            volume.setName(vNs[1]);
            //标记
            serialNumSet.add(vNs[0]);
            System.out.println("SerialNum:" + volume.getSerialNum() + ",name:" + volume.getName());
            //正常卷解析
            parseNormalVolume(vsObj, vId, chapterList, volumeChapterList);
        } else {
            volume.setSerialNum("");
            volume.setName(vN);
            System.out.println("其他卷解析:" + vN);
            System.out.println("SerialNum:" + volume.getSerialNum() + ",name:" + volume.getName());
            //解析其他卷
            parseOtherVolume(vsObj, vId, chapterList, volumeChapterList);
        }
        //volumeList组装
        volumeList.add(volume);
        //书籍源与卷对应关系
        BookSourceVolume bookSourceVolume = new BookSourceVolume();
        bookSourceVolume.setId(IDGenarator.getID());
        bookSourceVolume.setBookSourceId(sourceId);
        bookSourceVolume.setVolumeId(volume.getId());
        //书籍源与卷组装
        bookSourceVolumeList.add(bookSourceVolume);
    }

    /**
     * 解析VIP卷
     *
     * @param vsObj                the vs obj
     * @param sourceId             the source id
     * @param volumeList           the volume list
     * @param bookSourceVolumeList the book source volume list
     * @param sort                 the sort
     * @param chapterList
     * @param volumeChapterList
     */
    private void parseVipVolume(Set<String> serialNumSet, JSONObject vsObj, String sourceId, List<Volume> volumeList, List<BookSourceVolume> bookSourceVolumeList, long sort, List<Chapter> chapterList, List<VolumeChapter> volumeChapterList) {
        //cs章节
        JSONArray cs = vsObj.getJSONArray("cs");
        //当前卷ID
        String vId = "";
        //循环每个章节
        for (int j = 0; j < cs.size(); j++) {
            Chapter chapter = new Chapter();
            JSONObject csObj = cs.getJSONObject(j);
            buildChapter(csObj, chapter);
            //章节名称 "第三卷 第二百五十三章 鬼灵门"  "有点急事，请假半日" "第四卷 风起海外 第四百一十二章 青竹小轩"  三种情况
            String cN = csObj.getString("cN");
            System.out.println("章节：" + cN);
            String[] sNs = cN.split("　+|\\s+", 4);
            if (sNs.length == 4) {
                //卷未处理
                if (!serialNumSet.contains(sNs[0]) && sNs[0].startsWith("第")) {
                    //标记
                    serialNumSet.add(sNs[0]);
                    Volume volume = new Volume();
                    volume.setSerialNum(sNs[0]);
                    volume.setName(sNs[1]);
                    vId = IDGenarator.getID();
                    volume.setId(vId);
                    volume.setSort(sort);
                    sort++;
                    System.out.println("SerialNum:" + volume.getSerialNum() + ",name:" + volume.getName());
                    //卷列表组装
                    volumeList.add(volume);

                    //书籍源与卷对应关系
                    BookSourceVolume bookSourceVolume = new BookSourceVolume();
                    bookSourceVolume.setId(IDGenarator.getID());
                    bookSourceVolume.setBookSourceId(sourceId);
                    bookSourceVolume.setVolumeId(vId);

                    //书籍源与卷组装
                    bookSourceVolumeList.add(bookSourceVolume);
                }
                chapter.setSerialNum(sNs[2]);
                chapter.setName(sNs[3]);
            } else if (sNs.length == 3) {
                //"第三卷 第二百五十三章 鬼灵门"
                chapter.setSerialNum(sNs[1]);
                chapter.setName(sNs[2]);
            } else {
                //"有点急事，请假半日"
                chapter.setSerialNum("");
                chapter.setName(cN);
            }
            VolumeChapter volumeChapter = new VolumeChapter();
            volumeChapter.setId(IDGenarator.getID());
            volumeChapter.setChapterId(chapter.getId());
            volumeChapter.setVolumeId(vId);
            //卷章节组装
            volumeChapterList.add(volumeChapter);
            //章节组装
            chapterList.add(chapter);
        }
    }


    /**
     * Parse other volume.
     *
     * @param vsObj       the vs obj
     * @param vId         the v id
     * @param chapterList the chapter list
     */
    private void parseOtherVolume(JSONObject vsObj, String vId, List<Chapter> chapterList, List<VolumeChapter> volumeChapterList) {
        //cs章节
        JSONArray cs = vsObj.getJSONArray("cs");
        //循环每个章节
        for (int j = 0; j < cs.size(); j++) {
            Chapter chapter = new Chapter();
            JSONObject csObj = cs.getJSONObject(j);
            buildChapter(csObj, chapter);
            //章节名称 "呵呵！终于上架了！"
            String cN = csObj.getString("cN");
            chapter.setSerialNum("");
            chapter.setName(cN);
            chapterList.add(chapter);
            VolumeChapter volumeChapter = new VolumeChapter();
            volumeChapter.setId(IDGenarator.getID());
            volumeChapter.setChapterId(chapter.getId());
            volumeChapter.setVolumeId(vId);
            volumeChapterList.add(volumeChapter);
        }
    }

    /**
     * Parse normal volume.
     *
     * @param vsObj       the vs obj
     * @param vId         the v id
     * @param chapterList the chapter list
     */
    private void parseNormalVolume(JSONObject vsObj, String vId, List<Chapter> chapterList, List<VolumeChapter> volumeChapterList) {
        //cs章节
        JSONArray cs = vsObj.getJSONArray("cs");
        //循环每个章节
        for (int j = 0; j < cs.size(); j++) {
            Chapter chapter = new Chapter();

            JSONObject csObj = cs.getJSONObject(j);
            buildChapter(csObj, chapter);
            //章节名称 "第一百七十一章 返回"
            String cN = csObj.getString("cN");
            String[] cNs = cN.split("　+|\\s+", 2);
            if (cNs.length == 2) {
                chapter.setSerialNum(cNs[0]);
                chapter.setName(cNs[1]);
            } else {
                chapter.setSerialNum("");
                chapter.setName(cN);
            }
            VolumeChapter volumeChapter = new VolumeChapter();
            volumeChapter.setId(IDGenarator.getID());
            volumeChapter.setChapterId(chapter.getId());
            volumeChapter.setVolumeId(vId);
            volumeChapterList.add(volumeChapter);
            chapterList.add(chapter);
        }
    }


    /**
     * Build chapter.
     *
     * @param csObj   the cs obj
     * @param chapter the chapter
     */
    private void buildChapter(JSONObject csObj, Chapter chapter) {
        //ID
        chapter.setId(IDGenarator.getID());
        //更新时间
        String uT = csObj.getString("uT");
        //阅读地址
        String cU = csObj.getString("cU");
        //uuid
        String uuid = csObj.getString("uuid");
        //章节字数
        String cnt = csObj.getString("cnt");
        chapter.setNumber(Integer.valueOf(cnt));
        //章节链接
        String url = "https://read.qidian.com/chapter/" + cU;
        chapter.setUrl(url);
        //首发时间 2008-08-02 09:18:37
        long dateTime = TimeUtil.stringToMilli(uT, TimeUtil.BASIC_DATE_TIME);
        chapter.setDateTime(dateTime);
        //序列
        long sort = Long.parseLong(uuid);
        chapter.setSort(sort);
    }
}