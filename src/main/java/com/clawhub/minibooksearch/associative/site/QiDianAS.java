package com.clawhub.minibooksearch.associative.site;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clawhub.minibooksearch.associative.ASInterface;
import com.clawhub.minibooksearch.constant.WebSiteConstant;
import com.clawhub.minibooksearch.core.http.HttpGenerator;
import com.clawhub.minibooksearch.core.http.HttpResInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <Description> 起点中文网的口子<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2019-03-04 22:38<br>
 */
@Component(WebSiteConstant.QI_DIAN)
public class QiDianAS implements ASInterface {
    /**
     * 获取书籍名称
     *
     * @param key 关键词
     * @return 书籍名称
     */
    @Override
    public List<String> searchBookName(String key) throws UnsupportedEncodingException {
        //请求上游
        HttpResInfo httpResInfo = HttpGenerator.sendGet("https://www.qidian.com/ajax/Search/AutoComplete?query=" + URLEncoder.encode(key, "utf-8"));
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
