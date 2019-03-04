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

    /**
     * 查找推荐榜根据榜类型和书籍类型
     * @param dataType
     * @param channel
     */
    void searchRecommendCollection(String dataType,String channel);

    /**
     * 实时获取章节信息
     *
     * @param webSite    站点
     * @param catalogUrl 目录地址
     * @param sourceId   书籍源ID
     * @return 章节信息
     */
    String searchChapter(String webSite, String catalogUrl, String sourceId);

    /**
     * 阅读章节
     *
     * @param webSite    站点
     * @param chapterUrl 章节url
     * @return 文章内容
     */
    String readChapter(String webSite, String chapterUrl);

}
