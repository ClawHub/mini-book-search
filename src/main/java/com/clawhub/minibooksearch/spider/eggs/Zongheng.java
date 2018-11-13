package com.clawhub.minibooksearch.spider.eggs;

import com.clawhub.minibooksearch.core.http.HttpGenerator;
import com.clawhub.minibooksearch.core.http.HttpResInfo;
import com.clawhub.minibooksearch.core.util.IDGenarator;
import com.clawhub.minibooksearch.core.util.TimeUtil;
import com.clawhub.minibooksearch.entity.BookInfo;
import com.clawhub.minibooksearch.entity.BookSource;
import com.clawhub.minibooksearch.entity.Chapter;
import com.clawhub.minibooksearch.entity.Recommend;
import com.clawhub.minibooksearch.spider.core.AbstractEgg;
import org.apache.http.cookie.Cookie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <Description>纵横中文网<br>
 *
 * @author DaShi<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/10/17 11:01 <br>
 */
@Component("www.zongheng.com")
public class Zongheng{
    /*
    @Override
    protected String getSearchUrl(String keyword) {
        String url = null;
        try {
            url = "https://search.zongheng.com/s?keyword=" + URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }

    @Override
    protected Recommend getRecommend(Element element){
        String name = element.select("").text();
        String dataType = element.select("").text();
        String channel = element.select("").text();
        long creatTime = Long.parseLong(TimeUtil.currentDateTime());
        return new Recommend(name,dataType,channel,creatTime);
    }

    @Override
    protected Elements getBookList(Document document) {
        return document.select(".search-result-list clearfix");
    }

    @Override
    protected BookInfo getBookInfo(Element element) {
        //获取书名
        String name = element.select(".fl").select(".se-result-infos").select("h2").select("a").text();
        //获取作者;
        String author = element.select("div.bookinfo>a").first().text();
        //获取图片url
        String picUrl = element.select(".imgbox").select(".fl").select(".se-result-book").select("a").attr("abs:href");
        //分类
        String classify = element.select("div.bookinfo>a").last().text();
        //状态
        String state = element.select("div.bookinfo>span").first().text();
        //简介
        String remark = element.select(".key-word").text();
        //总字数
        String numberStr = element.select("div.bookinfo>span").last().text();
        Integer number = null;
        if(null != numberStr){
            number = Integer.valueOf(numberStr);
        }
        return new BookInfo(name, author, "", classify, state, "", number, remark, picUrl);
    }

    @Override
    protected BookSource getBookSource(Element element) {
        //webSite
        String webSite = "www.zongheng.com";
        //url
        String url =  element.select("div.btn a.bkinfo").attr("abs:href");
        //dataBid
        String dataBid = element.select("div.btn a.addshelf").attr("data-bid");
        //目录链接
        String catalogUrl = url.replace("/book/","/showchapter/");
        return new BookSource(dataBid, url, "", catalogUrl, webSite);
    }

    @Override
    public Map<String, Chapter> chapter(String catalogUrl, String sourceId) throws IOException {
        Map<String, Chapter> chapters = new HashMap<>();
        int num = 0;
        //获取作品目录页面信息
        Document document = Jsoup.connect(catalogUrl).userAgent(
                "Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").get();
        //纵横目录页名为volume-list的div标签下嵌套很多div标签，没有name，id等标识，所以先全取得
        Elements elements =  document.select("div.volume-list").select("div");
        //这里我也不知道为啥get第4个，elements的size是19，我一点一点试的，章节部分就在4里面
        Elements chapterElements = elements.get(4).select("ul.chapter-list").select(".clearfix").select("li");
        for (Element element : chapterElements) {
            //章节链接
            String url = "";
            if (("vip col-4").equals(element.className())) {
                url = "vip用户可读章节无法获取";
            }else {
                url = element.select("a").attr("abs:href");
            }

            //获取a标签中title的值  第一章 小二上酒 字数：5083 更新时间：2012-06-29 13:16
            String title = element.select("a").attr("title");
            //首发时间
            String time = title.substring(title.indexOf("更新时间：")+5);
            long dateTime = TimeUtil.StringToMilli(time, TimeUtil.BASIC_DATE_TIME);
            //章节名称
            String name = title.substring(0,title.indexOf("字数")-1);
            //章节字数
            String number = title.substring(title.indexOf("字数")+3,title.indexOf("更新时间")-1);
            //序列 木有啊

            //章节数据
            logger.info("章节名称:{}", name);
            Chapter chapter = new Chapter();
            chapter.setDateTime(dateTime);
            chapter.setId(IDGenarator.getID());
            chapter.setName(name);
            chapter.setNumber(Integer.valueOf(number));
            chapter.setSourceId(sourceId);
            chapter.setUrl(url);

            //收集
            chapters.put(String.valueOf(num), chapter);
            num++;
        }
        return chapters;
    }

    @Override
    public String read(String chapterUrl) throws IOException {
        //请求
        HttpResInfo httpResInfo = HttpGenerator.sendGet(chapterUrl, 6000, 6000, null, false);
        if (httpResInfo.getSuccess()) {
            String html = httpResInfo.getResult();
            Document document = Jsoup.parse(html);
            Elements elements  = document.select("div.content").select("p");
            StringBuilder stringBuffer = new StringBuilder();
            for (Element element : elements) {
                stringBuffer.append(element.text());
                stringBuffer.append("\n");
            }
            return stringBuffer.toString();

        }
        return null;
    }
    */
}
