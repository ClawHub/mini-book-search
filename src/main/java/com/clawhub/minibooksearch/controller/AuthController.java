package com.clawhub.minibooksearch.controller;

import com.clawhub.minibooksearch.auth.AuthUtil;
import com.clawhub.minibooksearch.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <Description> 微信小程序登陆网关<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2018-10-25 21:24<br>
 */
@RestController
@RequestMapping("auth")
public class AuthController {
    /**
     * The Person service.
     */
    @Autowired
    private AuthService authService;

    /**
     * code2Session
     *
     * @param code the code
     * @return the string
     */
    @GetMapping("getToken/{code}")
    public String getToken(@PathVariable("code") String code) {
        return authService.code2Session(code, AuthUtil.getToken());
    }

}
