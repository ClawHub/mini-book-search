package com.clawhub.minibooksearch.controller;

import com.alibaba.fastjson.JSONObject;
import com.clawhub.minibooksearch.associative.AssociativeSearch;
import com.clawhub.minibooksearch.core.result.ResultUtil;
import com.clawhub.minibooksearch.core.util.CommonUtil;
import com.clawhub.minibooksearch.recommend.RecommendSearch;
import com.clawhub.minibooksearch.service.BookService;
import com.clawhub.minibooksearch.service.SpiderService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <Description> 数据网关<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/10/11 <br>
 */
@RestController
@RequestMapping("book")
public class BookController {

    /**
     * The Logger.
     */
    private Logger logger = LoggerFactory.getLogger(BookController.class);

    /**
     * The Book service.
     */
    @Autowired
    private BookService bookService;

    /**
     * The Spider service.
     */
    @Autowired
    private SpiderService spiderService;

    /**
     * The Recommend search.
     */
    @Autowired
    private RecommendSearch recommendSearch;
    /**
     * 搜索联想服务
     */
    @Autowired
    private AssociativeSearch associativeSearch;

    /**
     * 推荐书籍
     *
     * @param param the param
     * @return the string
     */
    @PostMapping("recommend")
    public String recommend(@RequestBody String param) {
        JSONObject body = JSONObject.parseObject(param);
        int pageNum = body.getIntValue("pageNum");
        int pageSize = body.getIntValue("pageSize");
        String dataType = body.getString("dataType");
        String channel = body.getString("channel");
        Map<String, String> map = CommonUtil.checkRecommend(dataType, channel);
        //爬虫异步操作
        spiderService.searchRecommendCollection(map.get("dataType"), map.get("channel"));

        //查询数据库
        return bookService.recommend(pageNum, pageSize, map.get("dataType"), map.get("channel"));
    }


    /**
     * Search book source string.
     *
     * @param param the param
     * @return the string
     */
    @PostMapping("searchBookSource")
    public String searchBookSource(@RequestBody String param) {
        JSONObject body = JSONObject.parseObject(param);
        String bookId = body.getString("bookId");
        return bookService.searchBookSource(bookId);
    }

    /**
     * Search chapter string.
     *
     * @param param the param
     * @return the string
     */
    @PostMapping("searchChapter")
    public String searchChapter(@RequestBody String param) {
        JSONObject body = JSONObject.parseObject(param);
        String sourceId = body.getString("sourceId");
        String catalogUrl = body.getString("catalogUrl");
        String webSite = body.getString("webSite");
        return spiderService.searchChapter(webSite, catalogUrl, sourceId);
    }

    /**
     * Read chapter string.
     *
     * @param param the param
     * @return the string
     */
    @PostMapping("readChapter")
    public String readChapter(@RequestBody String param) {
        JSONObject body = JSONObject.parseObject(param);
        String chapterUrl = body.getString("chapterUrl");
        String webSite = body.getString("webSite");
        return spiderService.readChapter(webSite, chapterUrl);
    }

    /**
     * 搜索联想功能
     *
     * @param key the key
     * @return the string
     */
    @GetMapping("/associativeSearch/{key}")
    public String associativeSearch(@PathVariable String key) {
        if (StringUtils.isBlank(key)) {
            return ResultUtil.getSucc();
        }
        return ResultUtil.getSucc(associativeSearch.searchBookName(key));
    }

    /**
     * 推荐
     *
     * @return the string
     */
    @GetMapping("recommend")
    public String recommend() {
        return ResultUtil.getSucc(recommendSearch.recommendBook());
    }

    /**
     * 搜索书籍信息
     *
     * @param param the param
     * @return the string
     */
    @PostMapping("searchBookInfo")
    public String searchBookInfo(@RequestBody String param) {
        JSONObject body = JSONObject.parseObject(param);
        int pageNum = body.getIntValue("pageNum");
        int pageSize = body.getIntValue("pageSize");
        String keyWord = body.getString("name");
        return bookService.searchBookInfo(pageNum, pageSize, keyWord);
    }
}
