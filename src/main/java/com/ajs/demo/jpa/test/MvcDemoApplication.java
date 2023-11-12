package com.ajs.demo.jpa.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;

@SpringBootApplication(exclude = MustacheAutoConfiguration.class)
//@SpringBootApplication(exclude = ThymeleafAutoConfiguration.class)
public class MvcDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MvcDemoApplication.class, args);
    }
}
