package com.cuit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.cuit"})
@MapperScan(basePackages = {"com.cuit.model","generator"})
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class,args);
    }
}