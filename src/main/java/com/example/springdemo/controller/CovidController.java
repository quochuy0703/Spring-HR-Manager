package com.example.springdemo.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springdemo.entity.Covid;
import com.example.springdemo.entity.Injection;
import com.example.springdemo.entity.Temp;
import com.example.springdemo.entity.User;
import com.example.springdemo.service.MultiService;
import com.example.springdemo.utils.UserPDFExport;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping("/covid")
public class CovidController {
    
    @Autowired
    private MultiService multiService;

    @GetMapping("")
    public String showCovidPage(Model theModel, HttpServletRequest request){

        UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = multiService.findUserByEmail(userdetail.getUsername());


        Temp theTemp = new Temp();
        theTemp.setTemp(37.0f);
        theModel.addAttribute("temp", theTemp);
        theModel.addAttribute("user", theUser);
        return "covid/covid";

    }

    @PostMapping("/temp")
    public String processTemp(@ModelAttribute("temp") Temp theTemp, Model theModel, HttpServletRequest request){

        UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = multiService.findUserByEmail(userdetail.getUsername());


        System.out.println(theTemp);
        if(theTemp.getDateTemp() == null){
            theTemp.setDateTemp(new Date());
        }

        theUser.addTemp(theTemp);

        multiService.saveTemp(theTemp);
        
        return "redirect:/covid";
    }

    @GetMapping("/injection")
    public String showInjectionPage(Model theModel, HttpServletRequest request){

        UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = multiService.findUserByEmail(userdetail.getUsername());


        Injection theInjection = new Injection();
        
        theModel.addAttribute("injection", theInjection);
        theModel.addAttribute("user", theUser);
        return "covid/injection";

    }

    @PostMapping("/injection")
    public String processInjection(@ModelAttribute("injection") Injection theInjection, Model theModel, HttpServletRequest request){

        UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = multiService.findUserByEmail(userdetail.getUsername());

        System.out.println(theInjection);
        if(theInjection.getDateInjection() == null){
            theInjection.setDateInjection(new Date());
        }

        theUser.addInjection(theInjection);

        multiService.saveInjection(theInjection);
        
        return "redirect:/covid/injection";
    }

    @GetMapping("/info-covid")
    public String showCovidInfoPage(Model theModel, HttpServletRequest request){
        
        UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = multiService.findUserByEmail(userdetail.getUsername());


        Covid theCovid = new Covid();
        theModel.addAttribute("covid", theCovid);
        theModel.addAttribute("user", theUser);
        return "covid/info";

    }

    @PostMapping("/info-covid")
    public String processCovidInfo(@ModelAttribute("covid") Covid theCovid, Model theModel, HttpServletRequest request){

        UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = multiService.findUserByEmail(userdetail.getUsername());

        
        if(theCovid.getDateCovid() == null){
            theCovid.setDateCovid(new Date());
        }

        theUser.addCovid(theCovid);

        multiService.saveCovid(theCovid);
        
        return "redirect:/covid/info-covid";
    }

    @GetMapping("/info-staff")
    public String ShowInfoStaff(Model theModel){

        UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = multiService.findUserByEmail(userdetail.getUsername());

        List<User> theUsers = multiService.findEmployeeByDepartment(theUser.getDepartment());

        theModel.addAttribute("users", theUsers);

        return "covid/covid-staff";
    }

    @GetMapping("/staff/pdf/{userId}")
    public void exportToPDF(@PathVariable("userId")int theId,HttpServletResponse response) throws DocumentException, IOException {

       

        User theUser = multiService.findUserById(theId);

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
         
        UserPDFExport exporter = new UserPDFExport();
        exporter.exportCovidPdf(response, theUser);
         
    }

    @GetMapping("/pdf/all-staff")
    public void exportAllStaffToPDF(HttpServletResponse response) throws DocumentException, IOException {

       

        UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = multiService.findUserByEmail(userdetail.getUsername());

        List<User> theUsers = multiService.findEmployeeByDepartment(theUser.getDepartment());

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
         
        UserPDFExport exporter = new UserPDFExport();
        exporter.exportALLStaffCovidPdf(response, theUsers);
         
    }
}
