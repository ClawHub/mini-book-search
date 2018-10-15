package com.clawhub.minibooksearch.spider.queue;

import com.clawhub.minibooksearch.core.spring.SpringContextHelper;
import com.clawhub.minibooksearch.spider.core.Egg;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <Description>MessageReceiver<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/10/15 10:36 <br>
 */
@Component
public class MessageReceiver {

    /**
     * 接收消息的方法
     *
     * @param message the message
     */
    public void receiveMessage(String message) {
        System.out.println("收到一条消息：" + message);
        //获取所有爬虫
        Map<String, Egg> eggMap = SpringContextHelper.getApplicationContext().getBeansOfType(Egg.class);
        for (Egg eggEntry : eggMap.values()) {
            eggEntry.touch(message);
        }
    }
}