package com.clawhub.minibooksearch.service.impl;

import com.clawhub.minibooksearch.service.SpiderService;
import com.clawhub.minibooksearch.spider.queue.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
