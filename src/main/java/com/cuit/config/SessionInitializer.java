package com.cuit.config;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * @author : CLEAR Li
 * @version : V1.0
 * @className : SessionInitializer
 * @packageName : com.cuit.config
 * @description : 一般类
 * @date : 2020-06-08 16:28
 **/
public class SessionInitializer extends AbstractHttpSessionApplicationInitializer {
    public SessionInitializer() {
        super(SessionConfig.class);
    }
}