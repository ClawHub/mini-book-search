package com.clawhub.minibooksearch.recommend.impl;

import com.clawhub.minibooksearch.core.spring.SpringContextHelper;
import com.clawhub.minibooksearch.recommend.IRecommend;
import com.clawhub.minibooksearch.recommend.site.RecommendSpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <Description> 爬虫获取推荐书籍<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2019-03-05 21:27<br>
 */
@Component
public class SpiderRecommend implements IRecommend {
    /**
     * The Logger.
     */
    private Logger logger = LoggerFactory.getLogger(SpiderRecommend.class);

    /**
     * 推荐书籍获取
     *
     * @return 推荐书籍集合
     */
    @Override
    public Set<String> recommendBook() {

        Set<String> result = new HashSet<>();
        Map<String, RecommendSpider> recommendSpiderMap = SpringContextHelper.getApplicationContext().getBeansOfType(RecommendSpider.class);
        if (CollectionUtils.isEmpty(recommendSpiderMap)) {
            logger.error("recommend Spider Map is empty.");
            return result;
        }
        recommendSpiderMap.values().forEach((recommendSpider) -> {
            //搜集推荐书籍
            result.addAll(recommendSpider.recomend());
        });
        return result;
    }
}
