package com.clawhub.minibooksearch.service.impl;

import com.clawhub.minibooksearch.core.result.ResultUtil;
import com.clawhub.minibooksearch.core.spring.SpringContextHelper;
import com.clawhub.minibooksearch.entity.Chapter;
import com.clawhub.minibooksearch.service.SpiderService;
import com.clawhub.minibooksearch.spider.core.Egg;
import com.clawhub.minibooksearch.spider.queue.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
     * The Message sender.
     */
    @Autowired
    private MessageSender messageSender;

    @Override
    public void searchKeywordsCollection(String keyword) {
        messageSender.sendMessage(keyword);
    }

    @Override
    public void searchRecommendCollection(String dataType,String channel){
        String keyword = "recommend=" + dataType + "=" + channel;
        messageSender.sendRecommendMessage(keyword);
    }

    @Override
    public String searchChapter(String webSite, String catalogUrl, String sourceId)  {
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

}
