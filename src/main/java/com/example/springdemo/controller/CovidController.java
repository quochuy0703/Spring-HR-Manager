package com.example.springdemo.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springdemo.entity.Covid;
import com.example.springdemo.entity.Injection;
import com.example.springdemo.entity.Temp;
import com.example.springdemo.entity.User;
import com.example.springdemo.service.MultiService;

@Controller
@RequestMapping("/covid")
public class CovidController {
    
    @Autowired
    private MultiService multiService;

    @GetMapping("")
    public String showCovidPage(Model theModel, HttpServletRequest request){
        Temp theTemp = new Temp();
        User user = (User) request.getAttribute("user");
        theTemp.setTemp(37.0f);
        theModel.addAttribute("temp", theTemp);
        theModel.addAttribute("user", user);
        return "covid/covid";

    }

    @PostMapping("/temp")
    public String processTemp(@ModelAttribute("temp") Temp theTemp, Model theModel, HttpServletRequest request){

        User user = (User) request.getAttribute("user");
        System.out.println(theTemp);
        if(theTemp.getDateTemp() == null){
            theTemp.setDateTemp(new Date());
        }

        user.addTemp(theTemp);

        multiService.saveTemp(theTemp);
        
        return "redirect:/covid";
    }

    @GetMapping("/injection")
    public String showInjectionPage(Model theModel, HttpServletRequest request){
        Injection theInjection = new Injection();
        User user = (User) request.getAttribute("user");
        theModel.addAttribute("injection", theInjection);
        theModel.addAttribute("user", user);
        return "covid/injection";

    }

    @PostMapping("/injection")
    public String processInjection(@ModelAttribute("injection") Injection theInjection, Model theModel, HttpServletRequest request){

        User user = (User) request.getAttribute("user");
        System.out.println(theInjection);
        if(theInjection.getDateInjection() == null){
            theInjection.setDateInjection(new Date());
        }

        user.addInjection(theInjection);

        multiService.saveInjection(theInjection);
        
        return "redirect:/covid/injection";
    }

    @GetMapping("/info-covid")
    public String showCovidInfoPage(Model theModel, HttpServletRequest request){
        Covid theCovid = new Covid();
        User user = (User) request.getAttribute("user");
        theModel.addAttribute("covid", theCovid);
        theModel.addAttribute("user", user);
        return "covid/info";

    }

    @PostMapping("/info-covid")
    public String processCovidInfo(@ModelAttribute("covid") Covid theCovid, Model theModel, HttpServletRequest request){

        User user = (User) request.getAttribute("user");
        System.out.println(theCovid);
        if(theCovid.getDateCovid() == null){
            theCovid.setDateCovid(new Date());
        }

        user.addCovid(theCovid);

        multiService.saveCovid(theCovid);
        
        return "redirect:/covid/info-covid";
    }
}
