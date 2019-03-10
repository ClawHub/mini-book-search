package com.clawhub.minibooksearch.mapper;

import com.clawhub.minibooksearch.common.IMapper;
import com.clawhub.minibooksearch.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 章节mapper
 */
public interface ChapterMapper extends IMapper<Chapter> {
    /**
     * 批量插入
     *
     * @param chapterList 章节
     */
    void batchInsert(@Param("list") List<Chapter> chapterList);

    /**
     * 根据卷ID获取章节列表
     *
     * @param volumeId 卷ID
     * @return 章节列表
     */
    List<Chapter> searchChaptersByVolumeId(@Param("volumeId") String volumeId);
}