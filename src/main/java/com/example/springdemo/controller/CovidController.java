package com.example.springdemo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

        theModel.addAttribute("path", "/covid");
        theModel.addAttribute("pageTitle", "Thông tin nhiệt độ");
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
        
        theModel.addAttribute("path", "/covid");
        theModel.addAttribute("pageTitle", "Thông tin mũi tiêm");
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

        theModel.addAttribute("path", "/covid");
        theModel.addAttribute("pageTitle", "Thông tin Covid");
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
    @PreAuthorize("hasAuthority('MANAGER')")
    public String ShowInfoStaff(Model theModel){

        UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = multiService.findUserByEmail(userdetail.getUsername());

        List<User> theUsers = multiService.findEmployeeByDepartment(theUser.getDepartment());

        theModel.addAttribute("path", "/covid");
        theModel.addAttribute("pageTitle", "Thông tin nhân viên");
        theModel.addAttribute("users", theUsers);

        return "covid/covid-staff";
    }

    @GetMapping("/staff/pdf/{userId}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public Object exportToPDF(@PathVariable("userId")int theId,HttpServletResponse response, HttpServletRequest request) throws DocumentException, IOException {

        UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = multiService.findUserByEmail(userdetail.getUsername());

        User theUserStaff = multiService.findUserById(theId);

       if(!theUser.getDepartment().equals(theUserStaff.getDepartment())){

        throw new AccessDeniedException("Unauthorised Access!");
       }
        UserPDFExport exporter = new UserPDFExport();

        
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        // response.setContentType("application/pdf");
        // String headerKey = "Content-Disposition";
        // String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        // response.setHeader(headerKey, headerValue);
         
        // exporter.exportCovidPdf(response, theUserStaff);
        // IOUtils.copy(new ByteArrayInputStream(exporter.exportCovidPdfByteArray(theUserStaff).toByteArray()), response.getOutputStream());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=users_" + currentDateTime + ".pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(exporter.exportCovidPdfByteArray(theUserStaff).toByteArray());
         
    }

    
    @GetMapping("/pdf/all-staff")
    @PreAuthorize("hasAuthority('MANAGER')")
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
