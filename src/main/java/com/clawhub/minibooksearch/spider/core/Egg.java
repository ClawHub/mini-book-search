package com.clawhub.minibooksearch.spider.core;

/**
 * <Description>爬虫统一接口<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/10/15 10:45 <br>
 */
public interface Egg {
    /**
     * 爬虫入口
     *
     * @param keyword 关键词
     */
    void touch(String keyword);
}
