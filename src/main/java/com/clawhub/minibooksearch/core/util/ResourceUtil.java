package com.clawhub.minibooksearch.core.util;

import com.clawhub.minibooksearch.core.spring.SpringContextHelper;
import org.springframework.context.ApplicationContext;

import java.util.Locale;


/**
 * <Description> 读取资源文件工具类 <br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018年02月05日<br>
 */
public class ResourceUtil {

    /**
     * Spring上下文
     */
    private static ApplicationContext context = SpringContextHelper.getApplicationContext();

    /**
     * 根据错误码获取错误信息.
     *
     * @param msgKey the msg key
     * @param args   the args
     * @return the message
     */
    public static String getMessage(String msgKey, Object... args) {
        return context.getMessage(msgKey, args, msgKey, Locale.CHINA);
    }

    /**
     * 根据错误码获取错误信息
     *
     * @param msgKey the msg key
     * @param locale the locale
     * @param args   the args
     * @return the message
     */
    public static String getMessage(String msgKey, Locale locale, Object... args) {
        return context.getMessage(msgKey, args, msgKey, locale);
    }

}
