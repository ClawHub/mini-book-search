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
 * <Description> 豆瓣<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2019-03-04 22:40<br>
 */
@Component(WebSiteConstant.DOU_BAN)
public class DouBanAS implements ASInterface {
    /**
     * 获取书籍名称
     *
     * @param key 关键词
     * @return 书籍名称
     */
    @Override
    public List<String> searchBookName(String key) throws UnsupportedEncodingException {
        //请求上游
        HttpResInfo httpResInfo = HttpGenerator.sendGet("https://book.douban.com/j/subject_suggest?q=" + URLEncoder.encode(key, "utf-8"));
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
        JSONArray jsonArray = JSONArray.parseArray(result);
        if (jsonArray == null || jsonArray.isEmpty()) {
            return list;
        }
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            String title = jsonObject.getString("title");
            if (StringUtils.isNotBlank(title)) {
                list.add(title);
            }
        }
        return list;
    }

}
