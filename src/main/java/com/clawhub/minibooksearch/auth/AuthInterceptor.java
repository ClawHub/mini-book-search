package com.clawhub.minibooksearch.auth;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <Description> 鉴权拦截<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2018-12-18 21:20<br>
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
    /**
     * The String redis template.
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * The Logger.
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = httpServletRequest.getHeader("token");
        if (StringUtils.isBlank(token)) {
            httpServletResponse.sendError(HttpStatus.SC_FORBIDDEN);
            return false;
        }
        logger.info("token:{}", token);
        String authInfo = stringRedisTemplate.opsForValue().get(token);
        if (StringUtils.isBlank(authInfo)) {
            httpServletResponse.sendError(HttpStatus.SC_FORBIDDEN);
            return false;
        }
        String[] strings = authInfo.split(":");
        String openId = strings[0];
        String sessionKey = strings[1];
        httpServletRequest.setAttribute("openId", openId);
        httpServletRequest.setAttribute("sessionKey", sessionKey);
        return true;
    }

}
