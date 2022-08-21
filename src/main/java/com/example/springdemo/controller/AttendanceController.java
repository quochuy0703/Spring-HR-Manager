package com.example.springdemo.controller;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springdemo.entity.AnnualLeave;
import com.example.springdemo.entity.User;
import com.example.springdemo.entity.WorkHour;
import com.example.springdemo.service.MultiService;

@Controller
@RequestMapping("/")
public class AttendanceController {

    @Autowired
    private MultiService multiService;
    
    @GetMapping("/attendance")
    public String showPage(HttpServletRequest request, Model theModel){
        

        User user = (User) request.getAttribute("user");
        System.out.println(user.getCurrentWork());
        WorkHour workHour = new WorkHour();

        theModel.addAttribute("workHour", workHour);
        theModel.addAttribute("user", user);

        theModel.addAttribute("path", "/");
        theModel.addAttribute("pageTitle", "Diem danh");

        return "attendance";
    }

    @PostMapping("/checkin")
    public String processCheckIn(@ModelAttribute("workHour") WorkHour workHour, HttpServletRequest request){

        User theUser = (User )request.getAttribute("user");

        workHour.setStartHour(new Date());
        theUser.addWorkHour(workHour);
        theUser.setWork(true);
        theUser.setCurrentWork(workHour);
        multiService.saveUser(theUser);

        
        return "redirect:/attendance";
    }

    @PostMapping("/checkout")
    public String processCheckOut(HttpServletRequest request){

        User theUser = (User )request.getAttribute("user");
        theUser.getCurrentWork().setEndHour(new Date());
        multiService.saveWorkHour(theUser.getCurrentWork());
        theUser.setCurrentWork(null);
        theUser.setWork(false);
        
        multiService.saveUser(theUser);
        
        return "redirect:/attendance";
    }

    @GetMapping("/leave")
    public String showLeave(Model theModel, HttpServletRequest request){

        User theUser = (User )request.getAttribute("user");

        AnnualLeave annualLeave = new AnnualLeave();

        theModel.addAttribute("user", theUser);
        theModel.addAttribute("path", "/");
        theModel.addAttribute("pageTitle", "Xin nghi phep");
        theModel.addAttribute("annualLeave", annualLeave);
        return "leave";
    }

    @PostMapping("/leave")
    public String showLeave(@ModelAttribute("annualLeve") AnnualLeave annualLeave, Model theModel, HttpServletRequest request){

        User theUser = (User )request.getAttribute("user");

        //tín toán số ngày nghỉ phép và lưu vào countDay
        long diffdate = annualLeave.getEndDate().getTime() - annualLeave.getStartDate().getTime();
        float countDay = TimeUnit.MILLISECONDS.toDays(diffdate) % 365;
        if (diffdate < 0) {
            
        }
        if (!annualLeave.isMorningStartDate()) {
            countDay = countDay - 0.5f;
        }
        if (!annualLeave.isMorningEndDate()) {
            countDay = countDay + 0.5f;
        }
        annualLeave.setCountDay(countDay);

        if(countDay > theUser.getAnnualLeave()){
            theModel.addAttribute("user", theUser);
            theModel.addAttribute("path", "/");
            theModel.addAttribute("pageTitle", "Xin nghi phep");
            theModel.addAttribute("errorMessage", "Nhap nghi phep khong thanh cong");
            theModel.addAttribute("annualLeave", annualLeave);

            return "/leave";
        }

        annualLeave.setUser(theUser);
        multiService.saveAnnualLeave(annualLeave);
        theUser.setAnnualLeave(theUser.getAnnualLeave() - countDay);
        multiService.saveUser(theUser);


        return "redirect:/leave";
    }
}
