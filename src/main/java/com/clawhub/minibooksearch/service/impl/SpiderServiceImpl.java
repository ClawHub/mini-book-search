package com.clawhub.minibooksearch.service.impl;

import com.clawhub.minibooksearch.core.result.ResultUtil;
import com.clawhub.minibooksearch.core.spring.SpringContextHelper;
import com.clawhub.minibooksearch.entity.Chapter;
import com.clawhub.minibooksearch.service.SpiderService;
import com.clawhub.minibooksearch.spider.core.Egg;
import com.clawhub.minibooksearch.spider.queue.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public String searchChapter(String webSite, String catalogUrl, String sourceId) {
        Egg egg = (Egg) SpringContextHelper.getBean(webSite);
        List<Chapter> chapters = egg.chapter(catalogUrl, sourceId);
        return ResultUtil.getSucc(chapters);
    }

    @Override
    public String readChapter(String webSite, String chapterUrl) {
        Egg egg = (Egg) SpringContextHelper.getBean(webSite);
        String content = egg.read(chapterUrl);
        return ResultUtil.getSucc(content);
    }

}
