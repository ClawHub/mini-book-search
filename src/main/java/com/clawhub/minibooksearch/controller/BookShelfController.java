package com.clawhub.minibooksearch.controller;

import com.alibaba.fastjson.JSONObject;
import com.clawhub.minibooksearch.core.result.ResultUtil;
import com.clawhub.minibooksearch.service.BookShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <Description> 书架网关<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2018-10-25 21:29<br>
 */
@RestController
@RequestMapping("bookShelf")
public class BookShelfController {
    /**
     * The Book shelf service.
     */
    @Autowired
    private BookShelfService bookShelfService;

    /**
     * Add book shelf string.
     *
     * @param param param
     * @return the string
     */
    @PostMapping("add")
    public String addBookShelf(@RequestBody String param) {
        JSONObject body = JSONObject.parseObject(param);
        String bookId = body.getString("bookId");
        String openId = body.getString("openId");
        return bookShelfService.addBookShelf(bookId, openId);
    }

    /**
     * Del book shelf string.
     *
     * @param param the param
     * @return the string
     */
    @PostMapping("del")
    public String delBookShelf(@RequestBody String param) {
        JSONObject body = JSONObject.parseObject(param);
        String bookId = body.getString("bookId");
        String openId = body.getString("openId");
        bookShelfService.delBookShelf(bookId, openId);
        return ResultUtil.getSucc();
    }

    /**
     * View book shelf string.
     *
     * @param param the param
     * @return the string
     */
    @PostMapping("view")
    public String viewBookShelf(@RequestBody String param) {
        JSONObject body = JSONObject.parseObject(param);
        String openId = body.getString("openId");
        return bookShelfService.viewBookShelf(openId);
    }

}
