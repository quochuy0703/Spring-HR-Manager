package com.example.springdemo;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import com.example.springdemo.interceptor.LogInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer  {

    @Autowired
    LogInterceptor logInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
        registry.addInterceptor(logInterceptor);
	}

    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory("images", registry);
    }
     
    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();
         
        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
         
        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/"+ uploadPath + "/");
    }

    
    
    
}
