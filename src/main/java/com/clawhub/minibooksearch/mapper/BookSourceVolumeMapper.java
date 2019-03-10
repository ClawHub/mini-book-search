package com.clawhub.minibooksearch.mapper;

import com.clawhub.minibooksearch.common.IMapper;
import com.clawhub.minibooksearch.entity.BookSourceVolume;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 书籍源-卷
 */
public interface BookSourceVolumeMapper extends IMapper<BookSourceVolume> {
    /**
     * 批量插入
     *
     * @param bookSourceVolumeList 书籍源-卷对应关系
     */
    void batchInsert(@Param("list") List<BookSourceVolume> bookSourceVolumeList);
}