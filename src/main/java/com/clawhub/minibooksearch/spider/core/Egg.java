package com.clawhub.minibooksearch.spider.core;

import com.clawhub.minibooksearch.entity.CatalogResult;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

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
    @Transactional(rollbackFor = Exception.class)
    EggResult touch(String keyword);

    /**
     * 同步阅读章节
     *
     * @param chapterUrl 章节url
     * @return 文本
     */
    String read(String chapterUrl) throws IOException;

    /**
     * 同步爬取目录
     *
     * @param catalogUrl 目录url
     * @param sourceId   书籍源ID
     * @return 结果
     */
    CatalogResult crawlCatalog(String catalogUrl, String sourceId);
}
