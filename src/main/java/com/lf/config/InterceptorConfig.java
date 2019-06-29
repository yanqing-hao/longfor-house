package com.lf.config;

import com.lf.interceptor.StaffInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    //在此处，将拦截器注册为一个 Bean
    @Bean
    public StaffInterceptor myInterceptor() {
        return new StaffInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor())
                .addPathPatterns("/skip/toMain")
             //放过拦截
                // index
                // 登录   到登录页面
                // static 静态资源
                // 去用户前台
     /*   "/",
                "/staffLogin/login","/skip/toStaffLogin",
                "/background*//**","/commons*//**","/foreground*//**",
                "/skip/toForeHome"*/
                .excludePathPatterns();
        super.addInterceptors(registry);
    }
}