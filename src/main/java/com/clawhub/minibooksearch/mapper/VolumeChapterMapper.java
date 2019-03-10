package com.clawhub.minibooksearch.mapper;

import com.clawhub.minibooksearch.common.IMapper;
import com.clawhub.minibooksearch.entity.VolumeChapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 卷-章节 mapper
 */
public interface VolumeChapterMapper extends IMapper<VolumeChapter> {
    /**
     * 批量插入卷-章节对应关系
     *
     * @param volumeChapterList 卷-章节对应关系
     */
    void batchInsert(@Param("list") List<VolumeChapter> volumeChapterList);
}