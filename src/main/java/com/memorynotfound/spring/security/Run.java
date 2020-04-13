package com.memorynotfound.spring.security;

import com.memorynotfound.spring.security.config.MethodSecurityConfig;
import com.memorynotfound.spring.security.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
// @ImportResource("classpath:spring-security-config.xml")
@Import({SecurityConfig.class, MethodSecurityConfig.class})
public class Run {

    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }
}
