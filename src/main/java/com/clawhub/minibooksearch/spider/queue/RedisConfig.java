package com.clawhub.minibooksearch.spider.queue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * <Description>redis消息监听器容器以及redis监听器注入IOC容器<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/10/15 10:37 <br>
 */
@Configuration
public class RedisConfig {
    /**
     * redis消息监听器容器
     * 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
     * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
     *
     * @param connectionFactory        the connection factory
     * @param bookListenerAdapter      the book listener adapter
     * @param recommendListenerAdapter the recommend listener adapter
     * @return redis message listener container
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter bookListenerAdapter, MessageListenerAdapter recommendListenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅了一个叫book 的通道
        container.addMessageListener(bookListenerAdapter, new PatternTopic("book"));
        container.addMessageListener(recommendListenerAdapter, new PatternTopic("recommend"));
        //这个container 可以添加多个 messageListener
        return container;
    }

    /**
     * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
     *
     * @param receiver the receiver
     * @return message listener adapter
     */
    @Bean
    MessageListenerAdapter bookListenerAdapter(MessageReceiver receiver) {
        //这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“receiveMessage”
        //也有好几个重载方法，这边默认调用处理器的方法 叫handleMessage 可以自己到源码里面看
        return new MessageListenerAdapter(receiver, "bookMessage");
    }

    @Bean
    MessageListenerAdapter recommendListenerAdapter(MessageReceiver receiver) {
        return new MessageListenerAdapter(receiver, "recommendMessage");
    }
}