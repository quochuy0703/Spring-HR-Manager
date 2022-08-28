package com.example.springdemo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.springdemo.dao.RepositoryRole;
import com.example.springdemo.entity.Role;
import com.example.springdemo.entity.UserLogin;

public class CustomUserLoginDetail implements UserDetails {

    private UserLogin userLogin;

    

    

    public CustomUserLoginDetail(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<Role> theRoles = userLogin.getRoles();

        List<SimpleGrantedAuthority> list = new ArrayList<>();

        for(Role role : theRoles){
            list.add(new SimpleGrantedAuthority(role.getName()));
        }

        System.out.println(list);
 
        return list;
    }

    @Override
    public String getPassword() {
        
        return userLogin.getPassword();
    }

    @Override
    public String getUsername() {
      
        return userLogin.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
  
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
       
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
       
        return true;
    }

    @Override
    public boolean isEnabled() {
      
        return true;
    }
    
}
