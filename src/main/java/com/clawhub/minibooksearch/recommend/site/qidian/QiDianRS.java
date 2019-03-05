package com.clawhub.minibooksearch.recommend.site.qidian;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clawhub.minibooksearch.core.http.HttpGenerator;
import com.clawhub.minibooksearch.core.http.HttpResInfo;
import com.clawhub.minibooksearch.recommend.site.RecommendSpider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <Description> 起点推荐爬虫<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2019-03-05 21:45<br>
 */
@Component
public class QiDianRS implements RecommendSpider {
    /**
     * 推荐获取
     *
     * @return 书籍列表
     */
    @Override
    public List<String> recomend() {
        //请求上游
        HttpResInfo httpResInfo = HttpGenerator.sendGet("https://www.qidian.com/ajax/Search/AutoComplete?query=");
        if (!httpResInfo.isSuccess()) {
            return Collections.emptyList();
        }
        //解析结果
        return parseResult(httpResInfo.getResult());
    }

    /**
     * 解析结果
     *
     * @param result the result
     * @return the list
     */
    private List<String> parseResult(String result) {
        List<String> list = new ArrayList<>();

        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject == null) {
            return list;
        }
        JSONArray suggestions = jsonObject.getJSONArray("suggestions");
        if (suggestions == null || suggestions.isEmpty()) {
            return list;
        }
        suggestions.forEach((bookObj) -> {
            JSONObject book = (JSONObject) bookObj;
            String value = book.getString("value");
            if (StringUtils.isNotBlank(value)) {
                list.add(value);
            }
        });

        return list;
    }
}
