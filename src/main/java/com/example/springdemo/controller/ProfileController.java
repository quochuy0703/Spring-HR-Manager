package com.example.springdemo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.springdemo.entity.User;
import com.example.springdemo.service.MultiService;
import com.example.springdemo.utils.MyFileUtils;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private MultiService multiService;
    
    @GetMapping("")
    public String showProfilePage(Model theModel){

        UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = multiService.findUserByEmail(userdetail.getUsername());

        theModel.addAttribute("user", theUser);
        
        return "profile/profile";
    }

    @GetMapping("/edit")
    public String showProfileEditPage(Model theModel){

        UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = multiService.findUserByEmail(userdetail.getUsername());
        
        theModel.addAttribute("path", "/profile");
        theModel.addAttribute("pageTitle", "Thông tin cá nhân");
        theModel.addAttribute("user", theUser);
        theModel.addAttribute("edit", true);
        
        return "profile/profile";
    }

    @PostMapping("/edit")
    public String saveUser(
            @RequestParam("image") MultipartFile multipartFile) throws IOException {
         
        UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = multiService.findUserByEmail(userdetail.getUsername());

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        theUser.setImageUrl(fileName);
         
        multiService.saveUser(theUser);
 
        String uploadDir = "images/" + theUser.getId();
 
        MyFileUtils.saveFile(uploadDir, fileName, multipartFile);
         
        return "profile/profile";
    }
}
