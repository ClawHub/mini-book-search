package com.clawhub.minibooksearch.core.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * <Description> token生成<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2018-12-14 20:46<br>
 */
public class TokenUtil {

    /**
     * Gets token.
     *
     * @param openId     the open id
     * @param sessionKey the session key
     * @return the token
     */
    public static String getToken(String openId, String sessionKey) {
        return DigestUtils.md5Hex(openId + sessionKey);
    }
}
