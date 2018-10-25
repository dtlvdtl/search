package com.ygb.Other;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class LoginConfig implements WebMvcConfigurer {

    /**
     * 配置静态资源
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
//        super.addResourceHandlers(registry);
    }
    @Bean
    public LoginInterceptor userInterceptor() {
        return new LoginInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns 用于添加拦截规则
        //excludePathPatterns 用于排除拦截
        registry.addInterceptor(userInterceptor())
                .addPathPatterns("/**")
//                .excludePathPatterns("/static/style/**","/static/image/**","/static/search.html","/other/getboard","/user/byactid","/user/login","/static/login.html","/user/bynameid");
                .excludePathPatterns("/static/style/**")
                .excludePathPatterns("/static/image/**")
                .excludePathPatterns("/static/search.html")//查询
                .excludePathPatterns("/other/getboard")//查询公告栏
                .excludePathPatterns("/user/byactid")//查询
                .excludePathPatterns("/user/login")//查询
                .excludePathPatterns("/static/login.html")
                .excludePathPatterns("/user/bynameid"); //登录页
//                .excludePathPatterns("/hlladmin/user/sendEmail") //发送邮箱
//                .excludePathPatterns("/hlladmin/user/register") //用户注册
//                .excludePathPatterns("/hlladmin/user/login"); //用户登录
//        super.addInterceptors(registry);
    }
}