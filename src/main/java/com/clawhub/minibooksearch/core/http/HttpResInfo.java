package com.clawhub.minibooksearch.core.http;

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
