package com.clawhub.minibooksearch.auth;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <Description> <br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2018-12-18 21:54<br>
 */
public class AuthUtil {
    /**
     * 获取请求体
     *
     * @return
     */
    private static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * Gets open id.
     *
     * @return the open id
     */
    public static String getOpenId() {
        return getRequest().getAttribute("openId").toString();
    }

    /**
     * Gets session key.
     *
     * @return the session key
     */
    public static String getSessionKey() {
        return getRequest().getAttribute("sessionKey").toString();
    }
}
