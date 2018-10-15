package com.clawhub.minibooksearch.service;

/**
 * <Description> 爬虫对外接口<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/10/11 <br>
 */
public interface SpiderService {

    /**
     * 搜集搜索关键词
     *
     * @param keyword 搜索关键词
     */
    void searchKeywordsCollection(String keyword);
}
