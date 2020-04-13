package com.memorynotfound.spring.security.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.memorynotfound.spring.security.auth.TokenAuthenticationFilter;

@Configuration
@Import(MethodSecurityConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
            .and()
                .withUser("admin").password("password").roles("ADMIN")
            .and()
            	.withUser("manager").password("password").roles("MANAGER");
    }
    
    @Bean
    public FilterRegistrationBean loggingFilter(){
        FilterRegistrationBean registrationBean 
          = new FilterRegistrationBean();
             
        registrationBean.setFilter(new TokenAuthenticationFilter());
        registrationBean.addUrlPatterns("/bank/*", "/book/*");
             
        return registrationBean;
    }

}