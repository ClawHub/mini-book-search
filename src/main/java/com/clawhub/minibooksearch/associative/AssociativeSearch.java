package com.clawhub.minibooksearch.associative;

import com.clawhub.minibooksearch.core.spring.SpringContextHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <Description> 联想搜索<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2019-03-04 22:25<br>
 */
@Component
public class AssociativeSearch {

    /**
     * The Logger.
     */
    private Logger logger = LoggerFactory.getLogger(AssociativeSearch.class);
    /**
     * 联想搜索爬取站点
     */
    @Value("#{'${associative.search.website.list}'.split(',')}")
    private List<String> associativeSearchWebSiteList;


    /**
     * 搜索联想
     *
     * @param key 关键词
     * @return 书名列表
     */
    public Set<String> searchBookName(String key) {
        //目前采用同步爬取
        Set<String> result = new HashSet<>();

        //如果没有配置爬取站点，返回空
        if (CollectionUtils.isEmpty(associativeSearchWebSiteList)) {
            return result;
        }
        associativeSearchWebSiteList.forEach((webSite) -> {
            logger.debug("Associative Search:{}", webSite);
            ASInterface asInterface = (ASInterface) SpringContextHelper.getBean(webSite);
            //获取数据
            List<String> bookNames;
            try {
                bookNames = asInterface.searchBookName(key);
                //放入集合
                result.addAll(bookNames);
            } catch (Exception e) {
                logger.error("Associative Search error", e);
            }
        });
        return result;
    }
}
