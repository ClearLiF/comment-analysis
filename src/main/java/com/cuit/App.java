package com.cuit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Hello world!
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.cuit"})
@MapperScan(basePackages = {"com.cuit","generator"})
//@EnableRedisHttpSession
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class,args);
    }
}