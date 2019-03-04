package com.clawhub.minibooksearch.associative.site;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clawhub.minibooksearch.associative.ASInterface;
import com.clawhub.minibooksearch.constant.WebSiteConstant;
import com.clawhub.minibooksearch.core.http.HttpGenerator;
import com.clawhub.minibooksearch.core.http.HttpResInfo;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * <Description> 纵横<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2019-03-04 22:39<br>
 */
@Component(WebSiteConstant.ZONG_HENG)
public class ZongHengAS implements ASInterface {
    /**
     * 获取书籍名称
     *
     * @param key 关键词
     * @return 书籍名称
     */
    @Override
    public List<String> searchBookName(String key) throws UnsupportedEncodingException {
        //请求上游
        Map<String, String> headMap = new HashMap<>();
        headMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36");
        HttpResInfo httpResInfo = HttpGenerator.sendGet("http://search.zongheng.com/search/suggest?keyword=" + URLEncoder.encode(key, "utf-8"), headMap);
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
        JSONArray books = jsonObject.getJSONArray("books");
        if (books == null || books.isEmpty()) {
            return list;
        }
        return books.toJavaList(String.class);
    }
}
