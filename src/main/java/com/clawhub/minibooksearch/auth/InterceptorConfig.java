package com.clawhub.minibooksearch.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <Description> 拦截配置<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2018-12-18 21:18<br>
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    /**
     * The Auth interceptor.
     */
    @Autowired
    private AuthInterceptor authInterceptor;

    /**
     * Add interceptors.
     *
     * @param registry the registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/bookShelf/**");
//                .excludePathPatterns("/auth/**");
    }
}
