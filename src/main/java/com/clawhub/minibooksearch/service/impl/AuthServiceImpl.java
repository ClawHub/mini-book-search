package com.clawhub.minibooksearch.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.clawhub.minibooksearch.core.http.HttpGenerator;
import com.clawhub.minibooksearch.core.http.HttpResInfo;
import com.clawhub.minibooksearch.core.result.ResultUtil;
import com.clawhub.minibooksearch.core.util.TokenUtil;
import com.clawhub.minibooksearch.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <Description> 登陆服务接口<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2018-12-14 20:18<br>
 */
@Service
public class AuthServiceImpl implements AuthService {
    /**
     * The Logger.
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * The App id.
     */
    @Value("${weixin.appid}")
    private String appId = "wx326a2b9bb09114a4";
    /**
     * The Secret.
     */
    @Value("${weixin.secret}")
    private String secret = "f5547092372c52a547db3d815bf510fa";
    /**
     * The Auth prefix.
     */
    @Value("${redis.key.prefix.auth}")
    private String authPrefix;
    /**
     * The String redis template.
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 登录凭证校验。通过 wx.login() 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程
     *
     * @param code 临时登录凭证 code
     * @return token
     */
    @Override
    public String code2Session(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        HttpResInfo httpResInfo = HttpGenerator.sendGet(url, 6000, 6000, null, false);
        if (httpResInfo.isSuccess()) {
            JSONObject body = JSONObject.parseObject(httpResInfo.getResult());
//            openid	string	用户唯一标识
//            session_key	string	会话密钥
//            unionid	string	用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
//            errcode	number	错误码
//            errmsg	string	错误信息
//            -1	系统繁忙，此时请开发者稍候再试
//            0	请求成功
//            40029	code 无效
//            45011	频率限制，每个用户每分钟100次
            int errCode = body.getInteger("errcode");
            if (errCode == 0) {
                //用户唯一标识
                String openId = body.getString("openid");
                //会话密钥
                String sessionKey = body.getString("session_key");
                //创建token
                String token = TokenUtil.getToken(openId, sessionKey);
                //token入redis
                stringRedisTemplate.opsForValue().set(authPrefix + token, openId + ":" + sessionKey);
                logger.error("获取token成功：{}", token);
                //返回token
                return ResultUtil.getSucc(token);
            }
            logger.error("微信小程序返回错误码：{},错误信息为：{}", errCode, body.getString("errmsg"));
        }
        logger.error("获取token失败！");
        return ResultUtil.getError("2001");
    }
}
