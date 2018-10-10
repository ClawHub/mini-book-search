package com.clawhub.minibooksearch.core.http;

/**
 * <Description> HttpConfig<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018 -08-14 <br>
 */
public class HttpConfig {

    /**
     * json-contentType
     */
    public static final String CONTENTTYPE_JSON = "application/json;charset=utf-8";
    /**
     * form-contentType
     */
    public static final String CONTENTTYPE_FORMDATA = "application/x-www-form-urlencoded;charset=UTF-8";

    /**
     * xml-contentType
     */
    public static final String CONTENTTYPE_XML = "text/xml;charset=utf-8";

    /**
     * http encode
     */
    public static final String HTTP_ENCODE = "utf-8";

    /**
     * contentTypeStr
     */
    public static final String CONTENT_TYPE_STR = "Content-Type";

    /**
     * 最大连接数
     */
    public static final int HTTP_CONNECTION_MAX_TOTAL = 500;
    /**
     * 每个路由基础的连接
     */
    public static final int HTTP_CONNECTION_MAX_ONE_ROUTE = 50;

    /**
     * 异常情况默认状态码
     */
    public static final int EXC_STATUS = 0;

    /**
     * private constructor
     */
    private HttpConfig() {

    }
}
