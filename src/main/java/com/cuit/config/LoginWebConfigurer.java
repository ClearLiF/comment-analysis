package com.cuit.config;

import com.cuit.controller.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author: 李琪
 * @date: 2020/3/16
 * @description 一般类
 */
@Slf4j
@Configuration
public class LoginWebConfigurer implements WebMvcConfigurer {

    private LoginInterceptor loginInterceptor;
    @Autowired
    public void setLoginInterceptor(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        log.info("配置拦截器");
        interceptorRegistry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(Arrays.asList("/static/**", "/res/**"))
                .excludePathPatterns("/bootstrapVaildator/**","/css/**","/js/**","/layui/**");/*.excludePathPatterns("/user/login",
                "/user/register"
                ,"/user/loginInto",
                "/user/registerSave",
                "/blog/toBlogHome","/blog/home","/user/checkUserName",
                "/user/getAddress","/blog/view","/comment/**","/page/**")*/

    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //  /uploads/  映射到D盘的uploads文件夹下
       // registry.addResourceHandler("/uploads/**").addResourceLocations("file:"+uploadDirReal);
      //  registry.addResourceHandler("/uploadTmp/**").addResourceLocations("file:"+uploadDir);
        //  js css 所在文件static映射，方便页面引用js css文件
    // registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    // registry.addResourceHandler("/src/main/").addResourceLocations("classpath:/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }
    @Override
    public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer contentNegotiationConfigurer) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer asyncSupportConfigurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {

    }





    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {

    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> list) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> list) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }
}
