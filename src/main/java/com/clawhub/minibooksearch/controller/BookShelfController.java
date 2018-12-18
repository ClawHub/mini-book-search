package com.clawhub.minibooksearch.controller;

import com.alibaba.fastjson.JSONObject;
import com.clawhub.minibooksearch.auth.AuthUtil;
import com.clawhub.minibooksearch.core.result.ResultUtil;
import com.clawhub.minibooksearch.service.BookShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return bookShelfService.addBookShelf(bookId, AuthUtil.getOpenId());
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
        bookShelfService.delBookShelf(bookId, AuthUtil.getOpenId());
        return ResultUtil.getSucc();
    }

    /**
     * View book shelf string.
     *
     * @return the string
     */
    @GetMapping("view")
    public String viewBookShelf() {
        return bookShelfService.viewBookShelf(AuthUtil.getOpenId());
    }

}
