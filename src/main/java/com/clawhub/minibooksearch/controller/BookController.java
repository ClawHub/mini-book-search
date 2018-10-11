package com.clawhub.minibooksearch.controller;

import com.alibaba.fastjson.JSONObject;
import com.clawhub.minibooksearch.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * The Book service.
     */
    @Autowired
    private BookService bookService;

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
        return bookService.recommend(pageNum, pageSize);
    }

    /**
     * Search book info string.
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
        return bookService.searchChapter(sourceId);
    }

}
