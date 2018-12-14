package com.clawhub.minibooksearch.core.util;

import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * <Description> token生成<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2018-12-14 20:46<br>
 */
public class TokenUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(TokenUtil.class);

    /**
     * Gets token.
     *
     * @param openId     the open id
     * @param sessionKey the session key
     * @return the token
     */
    public static String getToken(String openId, String sessionKey) {
        byte[] binaryData;
        try {
            binaryData = (openId + sessionKey).getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            //加密失败
            LOGGER.error("加密失败");
            return "";
        }
        return MD5Encoder.encode(binaryData);
    }
}
