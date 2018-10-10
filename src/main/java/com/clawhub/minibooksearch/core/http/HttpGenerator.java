package com.clawhub.minibooksearch.core.http;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <Description> HTTP工具<br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018/8/14 <br>
 */
public class HttpGenerator {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpGenerator.class);

    /**
     * 带线程池的httpclient
     */
    private static final CloseableHttpClient POOL_CLIENT;

    static {
        //生成带线程池的httpclient
        POOL_CLIENT = generatePoolingHttpClient();
    }

    /**
     * 生成带线程池的httpclient
     *
     * @return the closeable http client
     */
    private static CloseableHttpClient generatePoolingHttpClient() {
        // 忽略证书策略
        Registry<ConnectionSocketFactory> socketFactoryRegistry = geneRegistry();
        // 设置连接管理器
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        // 最大连接数
        connManager.setMaxTotal(HttpConfig.HTTP_CONNECTION_MAX_TOTAL);
        // 每个路由基础的连接
        connManager.setDefaultMaxPerRoute(HttpConfig.HTTP_CONNECTION_MAX_ONE_ROUTE);
        // 创建自定义的httpclient对象
        return HttpClients.custom().setConnectionManager(connManager).build();

    }

    /**
     * 生成httpclient
     *
     * @return the closeable http client
     */
    protected static CloseableHttpClient generateHttpClient() {
        // 忽略证书策略
        Registry<ConnectionSocketFactory> socketFactoryRegistry = geneRegistry();
        // 设置连接管理器
        HttpClientConnectionManager connManager = new BasicHttpClientConnectionManager(socketFactoryRegistry);
        // 创建自定义的httpclient对象
        return HttpClients.custom().setConnectionManager(connManager).build();

    }

    /**
     * 忽略证书策略
     *
     * @return the registry
     */
    private static Registry<ConnectionSocketFactory> geneRegistry() {
        // 采用绕过验证的方式处理https请求
        SSLContext sslcontext = createIgnoreVerifySSL();
        // 设置协议http和https对应的处理socket链接工厂的对象
        return RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext, NoopHostnameVerifier.INSTANCE)).build();
    }

    /**
     * 绕过验证（忽略证书）
     *
     * @return the ssl context
     */
    private static SSLContext createIgnoreVerifySSL() {
        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                                           String paramString) {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                                           String paramString) {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[0];
            }
        };
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSLv3");
            sc.init(null, new TrustManager[]{
                    trustManager
            }, null);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            LOGGER.error("ignoreVerifyCert exception, the msg is :", e);
        }
        return sc;
    }


    /**
     * 执行发送请求
     *
     * @param request   the request
     * @param url       the url
     * @param isUsePool 是否使用连接池
     * @return the final response
     */
    private static HttpResInfo getFinalResponse(HttpUriRequest request, String url, boolean isUsePool) {
        //获取httpclient
        CloseableHttpClient httpClient = isUsePool ? POOL_CLIENT : generateHttpClient();
        HttpResInfo httpResInfo;
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            // 正常连接到目标并获取到返回
            String body = getBody(response);
            LOGGER.info(" url is : {}, source result from upStream is : {}", shortUrl(url), body);
            int status = response.getStatusLine().getStatusCode();
            // 返回非200，打印日志
            if (HttpStatus.SC_OK != status) {
                LOGGER.error("http status is not 200, the status is :{}", status);
            }
            httpResInfo = getRsp(status, null, body);

        } catch (IOException e) {
            // 获取数据发生异常
            httpResInfo = getRsp(HttpConfig.EXC_STATUS, e, "");
            LOGGER.error("sendRequest exception", e);
        }
        return httpResInfo;
    }

    /**
     * Gets rsp.
     *
     * @param status the status
     * @param exc    the exc
     * @param result the result
     * @return the rsp
     */
    private static HttpResInfo getRsp(int status, Exception exc, String result) {
        HttpResInfo httpResInfo = new HttpResInfo();
        // 如果请求成功返回数据
        if (HttpStatus.SC_OK == status) {
            httpResInfo.setSuccess(true);
            httpResInfo.setResult(result);
        } else {
            httpResInfo.setSuccess(false);
            httpResInfo.setResult(result);
        }
        return httpResInfo;
    }

    /**
     * Generate response string.
     *
     * @param response the response
     * @return the string
     */
    private static String getBody(CloseableHttpResponse response) {
        // 获取结果实体
        String body = "";
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            // 按指定编码转换结果实体为String类型
            try {
                body = EntityUtils.toString(entity, HttpConfig.HTTP_ENCODE);
                // 如果repEntity不为空，则释放内存空间
                EntityUtils.consume(entity);
            } catch (Exception e) {
                LOGGER.error("generateResponse is fail", e);
            }
        }

        return body;
    }

    /**
     * 去除srcUrl中的敏感信息
     *
     * @param srcUrl the src url
     * @return the string
     */
    protected static String shortUrl(String srcUrl) {
        if (StringUtils.isEmpty(srcUrl) || !srcUrl.contains("?")) {
            return srcUrl;
        } else {
            return srcUrl.substring(0, srcUrl.indexOf('?'));
        }
    }


    /**
     * Send get http res info.
     *
     * @param url         the url
     * @param contimeout  the contimeout
     * @param readtimeout the readtimeout
     * @param headMap     the head map
     * @param isUsePool   the is use pool
     * @return the http res info
     */
    public static HttpResInfo sendGet(String url, int contimeout, int readtimeout, Map<String, String> headMap, boolean isUsePool) {
        // 创建get方式请求对象
        HttpGet httpGet = new HttpGet(url);
        // 设置超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readtimeout).setConnectTimeout(contimeout).build();
        httpGet.setConfig(requestConfig);
        // 增加特殊的请求头
        if (null != headMap && !headMap.isEmpty()) {
            for (Map.Entry<String, String> entry : headMap.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        }
        // 执行请求操作，并拿到结果（同步阻塞）
        return getFinalResponse(httpGet, url, isUsePool);
    }


    /**
     * Send form http res info.
     *
     * @param url         the url
     * @param map         the map
     * @param headMap     the head map
     * @param contimeout  the contimeout
     * @param readtimeout the readtimeout
     * @param isUsePool   the is use pool
     * @return the http res info
     */
    public static HttpResInfo sendForm(String url, Map<String, String> map, Map<String, String> headMap, int contimeout, int readtimeout, boolean isUsePool) {
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        // 设置超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readtimeout).setConnectTimeout(contimeout).build();
        httpPost.setConfig(requestConfig);
        // 装填参数
        List<NameValuePair> nvps = new ArrayList<>();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        // 设置参数到请求对象中
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, HttpConfig.HTTP_ENCODE));
        } catch (UnsupportedEncodingException e1) {
            LOGGER.error("sendForm UrlEncodedFormEntity exception, the msg is :", e1);
            return getRsp(HttpConfig.EXC_STATUS, e1, "");
        }
        // 设置header信息
        // 指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader(HttpConfig.CONTENT_TYPE_STR, HttpConfig.CONTENTTYPE_FORMDATA);
        // 增加特殊的请求头
        if (null != headMap && !headMap.isEmpty()) {
            for (Map.Entry<String, String> entry : headMap.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        // 执行请求操作，并拿到结果（同步阻塞）
        return getFinalResponse(httpPost, url, isUsePool);
    }


    /**
     * Send json http res info.
     *
     * @param url         the url
     * @param jsonStr     the json str
     * @param headMap     the head map
     * @param contimeout  the contimeout
     * @param readtimeout the readtimeout
     * @param isUsePool   the is use pool
     * @return the http res info
     */
    public static HttpResInfo sendJson(String url, String jsonStr, Map<String, String> headMap, int contimeout, int readtimeout, boolean isUsePool) {
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        // 设置超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readtimeout).setConnectTimeout(contimeout).build();
        httpPost.setConfig(requestConfig);
        // 设置参数到请求对象中
        StringEntity entity = new StringEntity(jsonStr, HttpConfig.HTTP_ENCODE);
        httpPost.setEntity(entity);
        // 设置header信息
        // 指定报文头【Content-type】
        httpPost.setHeader(HttpConfig.CONTENT_TYPE_STR, HttpConfig.CONTENTTYPE_JSON);
        // 增加特殊的请求头
        if (null != headMap && !headMap.isEmpty()) {
            for (Map.Entry<String, String> entry : headMap.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        // 执行请求操作，并拿到结果（同步阻塞）
        return getFinalResponse(httpPost, url, isUsePool);
    }


    /**
     * Send xml http res info.
     *
     * @param url         the url
     * @param postStr     the post str
     * @param headers     the headers
     * @param connTimeout the conn timeout
     * @param readTimeout the read timeout
     * @param isUsePool   the is use pool
     * @return the http res info
     */
    public static HttpResInfo sendXml(String url, String postStr, Map<String, String> headers, int connTimeout, int readTimeout, boolean isUsePool) {
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        // 设置超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeout)
                .setConnectTimeout(connTimeout).build();
        httpPost.setConfig(requestConfig);
        // 设置参数到请求对象中
        StringEntity entity = new StringEntity(postStr, HttpConfig.HTTP_ENCODE);
        httpPost.setEntity(entity);
        // 设置header信息
        // 指定报文头【Content-type】
        httpPost.setHeader(HttpConfig.CONTENT_TYPE_STR, HttpConfig.CONTENTTYPE_XML);
        // 增加特殊的请求头
        if (null != headers && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        // 执行请求操作，并拿到结果（同步阻塞）
        return getFinalResponse(httpPost, url, isUsePool);
    }

    /**
     * Send stream http res info.
     *
     * @param url         the url
     * @param postStr     the post str
     * @param headers     the headers
     * @param connTimeout the conn timeout
     * @param readTimeout the read timeout
     * @param isUsePool   the is use pool
     * @return the http res info
     */
    public static HttpResInfo sendStream(String url, String postStr, Map<String, String> headers, int connTimeout, int readTimeout, boolean isUsePool) {
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        // 设置超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeout)
                .setConnectTimeout(connTimeout).build();
        httpPost.setConfig(requestConfig);

        // 设置参数到请求对象中
        InputStream is = null;
        InputStreamEntity entity = null;
        try {
            is = new ByteArrayInputStream(postStr.getBytes(HttpConfig.HTTP_ENCODE));
            entity = new InputStreamEntity(is, is.available());
        } catch (IOException e) {
            // 形成参数发生异常
            LOGGER.error(" sendRequest exception", e);
            return getRsp(HttpConfig.EXC_STATUS, e, "");
        }
        httpPost.setEntity(entity);
        // 设置header信息
        // 指定报文头【Content-type】
        httpPost.setHeader(HttpConfig.CONTENT_TYPE_STR, HttpConfig.CONTENTTYPE_FORMDATA);
        // 增加特殊的请求头
        if (null != headers && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        // 执行请求操作，并拿到结果（同步阻塞）
        return getFinalResponse(httpPost, url, isUsePool);
    }


    /**
     * Send multi part http res info.
     *
     * @param url         the url
     * @param postMap     the post map
     * @param headers     the headers
     * @param connTimeout the conn timeout
     * @param readTimeout the read timeout
     * @param isUsePool   the is use pool
     * @return the http res info
     */
    public static HttpResInfo sendMultiPart(String url, Map<String, ContentBody> postMap, Map<String, String> headers, int connTimeout,
                                            int readTimeout, boolean isUsePool) {
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        // 设置超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(readTimeout)
                .setConnectTimeout(connTimeout).build();
        httpPost.setConfig(requestConfig);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        for (Map.Entry<String, ContentBody> entry : postMap.entrySet()) {
            builder.addPart(entry.getKey(), entry.getValue());
        }
        // 设置参数到请求对象中
        httpPost.setEntity(builder.build());

        // 增加特殊的请求头
        if (null != headers && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }

        // 执行请求操作，并拿到结果（同步阻塞）
        return getFinalResponse(httpPost, url, isUsePool);
    }
}
