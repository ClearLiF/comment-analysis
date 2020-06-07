package com.cuit.controller.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 无建议(默认)
 * @description  放行注释
 * @author ClearLi
 * @date 2020/6/7 18:35
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UnAuthRequest {
}
