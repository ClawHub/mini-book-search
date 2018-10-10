package com.clawhub.minibooksearch.core.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取容器中的ApplicationContext
 */
public class SpringContextHelper implements ApplicationContextAware {
    /**
     * appCtx
     */
    private static ApplicationContext appCtx;

    /**
     * Description:  此方法可以把ApplicationContext对象inject到当前类中作为一个静态成员变量 <br>
     *
     * @param applicationContext application context
     * @author LiZhiming <br>
     * @taskId <br>
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHelper.setAppCtx(applicationContext);
    }

    /**
     * Description: 此方法可以把ApplicationContext对象inject到当前类中作为一个静态成员变量。<br>
     *
     * @param applicationContext application context
     * @author LiZhiming <br>
     * @taskId <br>
     */
    private static void setAppCtx(ApplicationContext applicationContext) {
        appCtx = applicationContext;
    }

    /**
     * Description: 获取ApplicationContext <br>
     *
     * @return application context
     * @author LiZhiming <br>
     * @taskId <br>
     */
    public static ApplicationContext getApplicationContext() {
        return appCtx;
    }

    /**
     * Description: 这是一个便利的方法，帮助我们快速得到一个BEAN <br>
     *
     * @param beanName bean name
     * @return object bean
     * @author LiZhiming <br>
     * @taskId <br>
     */
    public static Object getBean(String beanName) {
        return appCtx.getBean(beanName);
    }

    /**
     * Description: 快速获取Spring容器中的bean<br>
     *
     * @param <T>   type parameter
     * @param clazz clazz
     * @return t bean
     * @author LiZhiming <br>
     * @taskId <br>
     */
    public static <T> T getBean(Class<T> clazz) {
        return appCtx.getBean(clazz);
    }
}
