package com.clawhub.minibooksearch.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * <Description> Mybatis通用Mapper接口<br>
 *
 * @param <T> <br>
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2017年11月17日 <br>
 */
public interface IMapper<T> extends Mapper<T>, MySqlMapper<T> {

}

