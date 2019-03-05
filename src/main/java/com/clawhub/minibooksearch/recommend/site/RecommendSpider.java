package com.clawhub.minibooksearch.recommend.site;

import java.util.List;

/**
 * <Description> 推荐爬虫接口<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2019-03-05 21:34<br>
 */
public interface RecommendSpider {
    /**
     * 推荐获取
     *
     * @return 书籍列表
     */
    List<String> recomend();
}
