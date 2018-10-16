package com.clawhub.minibooksearch.core.http;

import org.apache.http.cookie.Cookie;

import java.util.List;

/**
 * <Description> 响应封装<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/8/15 <br>
 */
public class HttpResInfo {

    private boolean success;
    private String result;
    private List<Cookie> cookies;

    public boolean isSuccess() {
        return success;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }

    public void setCookies(List<Cookie> cookies) {
        this.cookies = cookies;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
