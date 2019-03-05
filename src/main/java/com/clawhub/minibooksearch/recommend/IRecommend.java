package com.clawhub.minibooksearch.recommend;

import java.util.Set;

/**
 * <Description> 推荐接口<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2019-03-05 21:25<br>
 */
public interface IRecommend {
    /**
     * 推荐书籍获取
     *
     * @return 推荐书籍集合
     */
    Set<String> recommendBook();
}
