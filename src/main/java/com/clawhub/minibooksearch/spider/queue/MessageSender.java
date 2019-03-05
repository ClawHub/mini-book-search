package com.clawhub.minibooksearch.spider.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * <Description>消息发布者<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/10/15 10:36 <br>
 */
@Component
public class MessageSender {
    /**
     * The String redis template.
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * Send message.
     *
     * @param book book
     */
    public void sendBookMessage(String book) {
        stringRedisTemplate.convertAndSend("book", book);
//        AsyncToSyncUtil.transfer();
    }

    /**
     * 创建一个名为recommend的通道
     *
     * @param recommend recommend
     */
    public void sendRecommendMessage(String recommend) {
        stringRedisTemplate.convertAndSend("recommend", recommend);
    }
}