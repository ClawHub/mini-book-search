package com.clawhub.minibooksearch.mapper;

import com.clawhub.minibooksearch.common.IMapper;
import com.clawhub.minibooksearch.entity.BookInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * The interface Book info mapper.
 */
public interface BookInfoMapper extends IMapper<BookInfo> {
    /**
     * 根据关键词查询书籍信息查询书籍信息
     *
     * @param name 关键词
     * @return the list
     */
    List<BookInfo> searchBookInfo(@Param("name") String name);
}