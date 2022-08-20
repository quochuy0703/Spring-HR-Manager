package com.example.springdemo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.springdemo.entity.User;
import com.example.springdemo.service.MultiService;

@Component
public class LogInterceptor implements HandlerInterceptor  {

    @Autowired
    private MultiService multiService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
                long startTime = System.currentTimeMillis();
                System.out.println("\n-------- LogInterception.preHandle --- ");
                System.out.println("Request URL: " + request.getRequestURL());
                System.out.println("Start Time: " + System.currentTimeMillis());
        
                request.setAttribute("startTime", startTime);

                User theUser = multiService.findUserById(1);
                
                request.setAttribute("user", theUser);

                return true;
	}

    @Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
            System.out.println("\n-------- LogInterception.postHandle --- ");
            System.out.println("Request URL: " + request.getRequestURL());
	}

    @Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
            long startTime = (Long) request.getAttribute("startTime");

            long endTime = System.currentTimeMillis();
            System.out.println("Request URL: " + request.getRequestURL());
            System.out.println("End Time: " + endTime);
    
            System.out.println("Time Taken: " + (endTime - startTime));
	}
    
}
