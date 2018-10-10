package com.clawhub.minibooksearch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

/**
 * <Description> 国际化配置 <br>
 *
 * @author LiZhiming<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018年02月05日<br>
 */
@Configuration
public class LocaleConfig extends WebMvcConfigurerAdapter {

    /**
     * _3600 <br>
     */
    private static final int NUM_3600 = 3600;

    /**
     * Description: 多语言国际化 <br>
     *
     * @return locale resolver
     * @author LiZhiming <br>
     * @taskId <br>
     */
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver slr = new CookieLocaleResolver();
        slr.setDefaultLocale(Locale.CHINA);
        slr.setCookieMaxAge(NUM_3600);
        return slr;
    }

    /**
     * Description: 语言切换拦截器，设定切换参数 <br>
     *
     * @return locale change interceptor
     * @author LiZhiming <br>
     * @taskId <br>
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    /**
     * Description: 动态切换语言 <br>
     *
     * @param registry registry
     * @author LiZhiming <br>
     * @taskId <br>
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}