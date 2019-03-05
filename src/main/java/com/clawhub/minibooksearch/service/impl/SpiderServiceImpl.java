package com.clawhub.minibooksearch.service.impl;

import com.clawhub.minibooksearch.core.result.ResultUtil;
import com.clawhub.minibooksearch.core.spring.SpringContextHelper;
import com.clawhub.minibooksearch.entity.BookInfo;
import com.clawhub.minibooksearch.entity.Chapter;
import com.clawhub.minibooksearch.service.SpiderService;
import com.clawhub.minibooksearch.spider.core.Egg;
import com.clawhub.minibooksearch.spider.core.EggResult;
import com.clawhub.minibooksearch.spider.queue.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <Description> 爬虫接口实现<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/10/11 <br>
 */
@Service
public class SpiderServiceImpl implements SpiderService {

    /**
     * The Logger.
     */
    private Logger logger = LoggerFactory.getLogger(SpiderServiceImpl.class);
    /**
     * The Message sender.
     */
    @Autowired
    private MessageSender messageSender;

    @Override
    public void searchKeywordsCollection(String keyword) {
        messageSender.sendBookMessage(keyword);
    }

    @Override
    public void searchRecommendCollection(String dataType, String channel) {
        String keyword = "recommend=" + dataType + "=" + channel;
        messageSender.sendRecommendMessage(keyword);
    }

    @Override
    public String searchChapter(String webSite, String catalogUrl, String sourceId) {
        Egg egg = (Egg) SpringContextHelper.getBean(webSite);
        Map<String, Chapter> chapters = null;
        try {
            chapters = egg.chapter(catalogUrl, sourceId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultUtil.getSucc(chapters);
    }

    @Override
    public String readChapter(String webSite, String chapterUrl) {
        Egg egg = (Egg) SpringContextHelper.getBean(webSite);
        String content = null;
        try {
            content = egg.read(chapterUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultUtil.getSucc(content);
    }

    /**
     * 异步书籍爬取
     *
     * @param name 书籍名称
     */
    @Override
    public void asyncSearchKeywords(String name) {
        messageSender.sendBookMessage(name);
    }

    /**
     * 同步书籍爬取
     *
     * @param name 书籍名称
     * @return 书籍列表
     */
    @Override
    public List<BookInfo> syncSearchKeyword(String name) {
        List<BookInfo> bookInfoList = new ArrayList<>();
        Map<String, Egg> eggMap = SpringContextHelper.getApplicationContext().getBeansOfType(Egg.class);
        for (Egg eggEntry : eggMap.values()) {
            EggResult eggResult = eggEntry.touch(name);
            if (eggResult.isSuccess()) {
                bookInfoList.addAll(eggResult.getBookInfoList());
            } else {
                logger.error("同步书籍爬取失败：{}", eggResult.getMsg());
            }

        }
        return bookInfoList;
    }

}
