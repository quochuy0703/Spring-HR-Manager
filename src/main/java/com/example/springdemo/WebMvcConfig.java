package com.example.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.springdemo.interceptor.LogInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter  {

    @Autowired
    LogInterceptor logInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
        registry.addInterceptor(logInterceptor);
	}

    
    
    
}
