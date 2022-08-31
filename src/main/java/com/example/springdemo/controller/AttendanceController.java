package com.example.springdemo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.example.springdemo.utils.LeaveUtils;

@Controller
@RequestMapping("/")
public class AttendanceController {

    @Autowired
    private MultiService multiService;
    
    @GetMapping("/attendance")
    public String showPage(HttpServletRequest request, Model theModel){
        

        // User user = (User) request.getAttribute("user");

        UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = multiService.findUserByEmail(userdetail.getUsername());


        WorkHour workHour = new WorkHour();
        workHour.setWorkPlace("1");

        theModel.addAttribute("workHour", workHour);
        theModel.addAttribute("user", user);

        theModel.addAttribute("path", "/");
        theModel.addAttribute("pageTitle", "Diem danh");

        return "attendance";
    }

    @PostMapping("/checkin")
    public String processCheckIn(@ModelAttribute("workHour") WorkHour workHour, HttpServletRequest request){

        UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = multiService.findUserByEmail(userdetail.getUsername());

        workHour.setStartHour(new Date());
        theUser.addWorkHour(workHour);
        theUser.setWork(true);
        theUser.setCurrentWork(workHour);
        multiService.saveUser(theUser);

        
        return "redirect:/attendance";
    }

    @PostMapping("/checkout")
    public String processCheckOut(HttpServletRequest request){

        UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = multiService.findUserByEmail(userdetail.getUsername());


        theUser.getCurrentWork().setEndHour(new Date());
        multiService.saveWorkHour(theUser.getCurrentWork());
        theUser.setCurrentWork(null);
        theUser.setWork(false);
        
        multiService.saveUser(theUser);
        
        return "redirect:/attendance";
    }

    @GetMapping("/leave")
    public String showLeave(Model theModel, HttpServletRequest request){

         UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = multiService.findUserByEmail(userdetail.getUsername());

        AnnualLeave annualLeave = new AnnualLeave();

        theModel.addAttribute("user", theUser);
        theModel.addAttribute("path", "/");
        theModel.addAttribute("pageTitle", "Xin nghi phep");
        theModel.addAttribute("annualLeave", annualLeave);
        return "leave";
    }

    @PostMapping("/leave")
    public String showLeave(@ModelAttribute("annualLeve") AnnualLeave annualLeave, Model theModel, HttpServletRequest request){

        UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = multiService.findUserByEmail(userdetail.getUsername());

        float countDay = LeaveUtils.getCountLeave(annualLeave);
        annualLeave.setCountDay(countDay);

        if(countDay > theUser.getAnnualLeave() || countDay < 0){
            theModel.addAttribute("user", theUser);
            theModel.addAttribute("path", "/");
            theModel.addAttribute("pageTitle", "Xin nghỉ phép");
            theModel.addAttribute("errorMessage", "Vượt quá ngày nghỉ phép hiện có!");
            theModel.addAttribute("annualLeave", annualLeave);

            if(countDay<0){
                theModel.addAttribute("errorMessage", "Ngày kết thúc không thể nhỏ hơn ngày bắt đầu!");
            }

            return "/leave";
        }

        //Kiểm tra xem có trùng ngày nghỉ phép hay không
        ArrayList<AnnualLeave> listLeave = new ArrayList<>();
        listLeave.add(annualLeave);
        Map<String, Float> listAnnualLeave = LeaveUtils.getLeaveOnDay(listLeave, true) ;

        List<AnnualLeave> list = multiService.findAnnualLeaveByUserId(theUser.getId());
        Map<String, Float> listAnnualLeave1 = LeaveUtils.getLeaveOnDay(list, true) ;

        // System.out.println(listAnnualLeave);
        // System.out.println(listAnnualLeave1);

    
        if(LeaveUtils.checkSameLeave(listAnnualLeave, listAnnualLeave1)){
            theModel.addAttribute("user", theUser);
            theModel.addAttribute("path", "/");
            theModel.addAttribute("pageTitle", "Xin nghỉ phép");
            theModel.addAttribute("errorMessage", "Trùng ngày nghỉ phép đã đăng ký!");
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
