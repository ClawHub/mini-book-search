package com.clawhub.minibooksearch.service;

/**
 * <Description> 服务接口<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2018-12-14 20:16<br>
 */
public interface AuthService {
    /**
     * 登录凭证校验。通过 wx.login() 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程
     *
     * @param code     临时登录凭证 code
     * @param oldToken oldToken
     * @return token
     */
    String code2Session(String code, String oldToken);
}
