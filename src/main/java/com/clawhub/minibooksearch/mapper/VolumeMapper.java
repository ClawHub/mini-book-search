package com.clawhub.minibooksearch.mapper;

import com.clawhub.minibooksearch.common.IMapper;
import com.clawhub.minibooksearch.entity.Volume;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 卷mapper
 */
public interface VolumeMapper extends IMapper<Volume> {
    /**
     * 根据书籍源ID获取卷列表
     *
     * @param sourceId 书籍源ID
     * @return 卷列表
     */
    List<Volume> searchVolumesBySourceId(@Param("sourceId") String sourceId);

    /**
     * 批量插入卷列表
     *
     * @param volumeList 卷列表
     */
    void batchInsert(@Param("list") List<Volume> volumeList);
}