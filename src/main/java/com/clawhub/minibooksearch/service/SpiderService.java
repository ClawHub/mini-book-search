package com.clawhub.minibooksearch.service;

import com.clawhub.minibooksearch.entity.BookInfo;
import com.clawhub.minibooksearch.entity.CatalogResult;

import java.util.List;

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

    /**
     * 查找推荐榜根据榜类型和书籍类型
     *
     * @param dataType
     * @param channel
     */
    void searchRecommendCollection(String dataType, String channel);

    /**
     * 阅读章节
     *
     * @param webSite    站点
     * @param chapterUrl 章节url
     * @return 文章内容
     */
    String readChapter(String webSite, String chapterUrl);

    /**
     * 异步书籍爬取
     *
     * @param name 书籍名称
     */
    void asyncSearchKeywords(String name);

    /**
     * 同步书籍爬取
     *
     * @param name 书籍名称
     * @return 书籍列表
     */
    List<BookInfo> syncSearchKeyword(String name);

    /**
     * 同步爬取
     *
     * @param webSite    站点
     * @param catalogUrl 目录URL
     * @param sourceId   书籍源ID
     * @return 结果
     */
    CatalogResult crawlCatalog(String webSite, String catalogUrl, String sourceId);
}
