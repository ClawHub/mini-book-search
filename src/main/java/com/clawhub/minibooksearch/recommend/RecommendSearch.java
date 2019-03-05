package com.clawhub.minibooksearch.recommend;

import com.clawhub.minibooksearch.core.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <Description> 热门推荐<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2019-03-05 19:58<br>
 */
@Component
public class RecommendSearch {

    /**
     * 当前推荐书籍池
     */
    private Set<String> recommendBookPool = Collections.emptySet();

    /**
     * 预期刷新时间，1小时刷新
     */
    private volatile long expectAtTime;
    /**
     * The Lock.
     */
    private ReentrantLock lock = new ReentrantLock();

    /**
     * The Recommend.
     */
    @Autowired
    private IRecommend iRecommend;

    /**
     * 获取推荐书籍名称
     *
     * @return the set
     */
    public Set<String> recommendBook() {
        //当前时间大于期望获取时间，重新获取推荐书籍
        if (System.currentTimeMillis() > expectAtTime) {
            //尝试获取锁，获取不到则直接跳过
            if (lock.tryLock()) {
                //一小时刷新一次
                expectAtTime = System.currentTimeMillis() + 60 * 60 * 1000;
                //推荐书籍
                recommendBookPool = iRecommend.recommendBook();
                //释放锁
                lock.unlock();
            }
        }
        //随机返回10条记录
        Set<String> set = new HashSet<>(10);
        for (int i = 0; i < 10; i++) {
            set.add(RandomUtil.INSTANCE.getRandomElement(recommendBookPool));
        }
        return set;
    }


}
