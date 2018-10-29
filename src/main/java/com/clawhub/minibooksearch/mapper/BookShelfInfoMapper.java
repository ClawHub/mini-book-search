package com.clawhub.minibooksearch.mapper;

import com.clawhub.minibooksearch.entity.BookShelfInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <Description> 书架上信息mapper<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2018-10-25 21:55<br>
 */
public interface BookShelfInfoMapper {
    /**
     * 预览书架
     *
     * @param openId openId
     * @return BookShelfInfo.list
     */
    List<BookShelfInfo> viewBookShelf(@Param("openId") String openId);
}
