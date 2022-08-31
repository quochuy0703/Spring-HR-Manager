package com.example.springdemo.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

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

import com.example.springdemo.SalaryForm;
import com.example.springdemo.WorkHourSession;
import com.example.springdemo.entity.User;
import com.example.springdemo.entity.WorkHour;
import com.example.springdemo.service.MultiService;
import com.example.springdemo.utils.LeaveUtils;
import com.example.springdemo.utils.WorkHourUtils;

@Controller
@RequestMapping("/work-hour")
public class WorkHourController {

  @Autowired
  private MultiService multiService;
    
    @GetMapping("")
    public String showWorkHourPage(Model theModel, HttpServletRequest request){

        
    

      UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      User theUser = multiService.findUserByEmail(userdetail.getUsername());

      Map<String, Float> theLeaves = LeaveUtils.getLeaveOnDay(theUser.getAnnualLeaves(), false);
      List<WorkHour> theList = theUser.getWorkHours();

      List<WorkHourSession> theListWorkHourSession = WorkHourUtils.getSessionWorkHour(theList, theLeaves);


      theModel.addAttribute("pageTitle", "Thông tin phiên làm việc");
      theModel.addAttribute("path", "/work-hour");
        theModel.addAttribute("user", theUser);
        theModel.addAttribute("sessionWorkHour", theListWorkHourSession);
        
        return "work-hour/work-hour";
    }


    @GetMapping("/annual-leaves")
    public String showLeavePage(Model theModel, HttpServletRequest request){
      UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      User theUser = multiService.findUserByEmail(userdetail.getUsername());

      
      theModel.addAttribute("pageTitle", "Thông tin nghỉ phép");
      theModel.addAttribute("path", "/work-hour");
      theModel.addAttribute("user", theUser);
      return "work-hour/annual-leave";
    }

    @GetMapping("/salary")
    public String showSalaryPage(Model theModel, HttpServletRequest request){
      UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      User theUser = multiService.findUserByEmail(userdetail.getUsername());

      
      SalaryForm salaryForm = new SalaryForm();
      theModel.addAttribute("salaryForm", salaryForm);
      theModel.addAttribute("pageTitle", "Thông tin lương");
      theModel.addAttribute("path", "/work-hour");
      theModel.addAttribute("user", theUser);
      return "work-hour/salary";
    }

    @PostMapping("/salary")
    public String processSalaryPage(@ModelAttribute("salaryForm") SalaryForm salaryForm, Model theModel, Principal principal){

      String monthSalary = salaryForm.getMonth();
      String yearSalary = salaryForm.getYear();

      UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      User theUser = multiService.findUserByEmail(userdetail.getUsername());

      Map<String, Float> theLeaves = LeaveUtils.getLeaveOnDay(multiService.findAnnualLeaveByIdAndByMonth(1, monthSalary, yearSalary), false);
      List<WorkHour> theList = multiService.findWorkHourByUserIdAndByMonth(1, monthSalary, yearSalary);
      if(theList.size() != 0 ){
        List<WorkHourSession> theListWorkHourSession = WorkHourUtils.getSessionWorkHour(theList, theLeaves);
        double salary = WorkHourUtils.getSalary(theListWorkHourSession, theUser.getSalaryScale());
        System.out.println(principal.getName()+": "+ salary); 
        theModel.addAttribute("salary", salary);
      }
    

      
      theModel.addAttribute("pageTitle", "Thông tin lương");
      theModel.addAttribute("path", "/work-hour");
      theModel.addAttribute("month", monthSalary);
      theModel.addAttribute("year", yearSalary);
      
      
      return "work-hour/salary";
    }
}
