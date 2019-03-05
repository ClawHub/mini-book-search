package com.clawhub.minibooksearch.spider.queue;

import com.clawhub.minibooksearch.core.spring.SpringContextHelper;
import com.clawhub.minibooksearch.spider.core.Egg;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


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
     * The Logger.
     */
    private Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    /**
     * The Thread pool.
     */
    private ExecutorService threadPool = new ThreadPoolExecutor(10, 10,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
            new BasicThreadFactory.Builder().namingPattern("message-receiver-pool-%d").daemon(true).build());

    /**
     * 接收消息的方法
     *
     * @param book book
     * @throws Exception the exception
     */
    public void bookMessage(String book) throws Exception {
        logger.info("异步爬取关键字：{}", book);
        //获取所有爬虫
        threadPool.execute(() -> {
            Map<String, Egg> eggMap = SpringContextHelper.getApplicationContext().getBeansOfType(Egg.class);
            for (Egg eggEntry : eggMap.values()) {
                eggEntry.touch(book);
            }
            logger.info("爬虫结束");
        });

    }

    /**
     * 接收消息的方法
     *
     * @param message the message
     * @throws Exception the exception
     */
    public void recommendMessage(String message) throws Exception {
        logger.info("收到一条消息：{}", message);
    }


    @PreDestroy
    public void preDestroy() {
        //关闭线程池
        threadPool.shutdown();
    }
}