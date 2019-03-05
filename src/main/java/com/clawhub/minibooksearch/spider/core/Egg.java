package com.clawhub.minibooksearch.spider.core;

import com.clawhub.minibooksearch.entity.Chapter;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;

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
     * 获取章节
     *
     * @param catalogUrl 目录url
     * @param sourceId   sourceId
     * @return 章节列表
     */
    Map<String, Chapter> chapter(String catalogUrl, String sourceId) throws IOException;

    /**
     * 阅读章节
     *
     * @param chapterUrl 章节url
     * @return 文本
     */
    String read(String chapterUrl) throws IOException;
}
