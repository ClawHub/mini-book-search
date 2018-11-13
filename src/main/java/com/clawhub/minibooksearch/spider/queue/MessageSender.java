package com.clawhub.minibooksearch.spider.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
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
     * @param keyword the keyword
     */
    public void sendMessage(String keyword) {
        stringRedisTemplate.convertAndSend("keyword", keyword);
    }

    /**
     * 创建一个名为recommend的通道
     * @param keyword
     */
    public void sendRecommendMessage(String keyword){
        stringRedisTemplate.convertAndSend("recommend", keyword);
    }
}