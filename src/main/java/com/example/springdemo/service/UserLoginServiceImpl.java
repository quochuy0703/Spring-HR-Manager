package com.example.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.springdemo.CustomUserLoginDetail;
import com.example.springdemo.dao.RepositoryUserLogin;
import com.example.springdemo.entity.UserLogin;

public class UserLoginServiceImpl implements UserDetailsService {

    @Autowired
    private RepositoryUserLogin repositoryUserLogin;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserLogin userLogin = repositoryUserLogin.findUserByEmail(username);

        if(userLogin == null){
            throw new UsernameNotFoundException("user not found");
        }
        
        return new CustomUserLoginDetail(userLogin);
    }
    
}
