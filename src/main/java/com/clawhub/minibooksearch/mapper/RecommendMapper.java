package com.clawhub.minibooksearch.mapper;

import com.clawhub.minibooksearch.common.IMapper;
import com.clawhub.minibooksearch.entity.Recommend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecommendMapper extends IMapper<Recommend> {
    /**
     * 根据推荐类型和书籍所属类型获得推荐榜单列表
     * @param dataType
     * @param channel
     * @return
     */
    List<Recommend> searchRecommend(@Param("dataType") String dataType,@Param("channel") String channel);
}