package com.example.springdemo.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;

import com.example.springdemo.WorkHourSession;
import com.example.springdemo.entity.User;
import com.example.springdemo.entity.WorkHour;
import com.example.springdemo.service.MultiService;
import com.example.springdemo.utils.LeaveUtils;
import com.example.springdemo.utils.WorkHourUtils;

@Controller
@RequestMapping("/manage")
public class ManagerController {

    @Autowired
    private MultiService multiService;
    
    @GetMapping("")
    public String showPageManage(Model theModel){
        UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User theUser = multiService.findUserByEmail(userdetail.getUsername());

        List<User> theUsers = multiService.findEmployeeByDepartment(theUser.getDepartment());

        theModel.addAttribute("path", "/manage");
        theModel.addAttribute("pageTitle", "Thông tin phê duyệt");
        theModel.addAttribute("users", theUsers);
        

        return "manage/manage-staff";
    }

    @GetMapping("/staff/show/{userId}")
    public String showWorkHourStaff(@PathVariable("userId")int theId,Model theModel){

        System.out.println(multiService.findWorkHourByUserIdAndByMonth(theId, "08", "2022") );


        theModel.addAttribute("path", "/manage");
        theModel.addAttribute("pageTitle", "Thông tin phê duyệt");
        theModel.addAttribute("isPost", false);
        theModel.addAttribute("sessionWorkHour", new ArrayList<>());
        theModel.addAttribute("staffId", theId);
        return "manage/manage-staff-workHour";
    }
    @PostMapping("/staff/show/{staffId}")
    public String processWorkHourStaff(@PathVariable("staffId")int theId,Model theModel){
        


      Map<String, Float> theLeaves = LeaveUtils.getLeaveOnDay(multiService.findAnnualLeaveByIdAndByMonth(theId, "08", "2022"),false);
      List<WorkHour> theList = multiService.findWorkHourByUserIdAndByMonth(theId, "08", "2022");
      List<WorkHourSession> theListWorkHourSession = WorkHourUtils.getSessionWorkHour(theList, theLeaves);
      
        
        theModel.addAttribute("isPost", true);
        theModel.addAttribute("sessionWorkHour", new ArrayList<>());
        theModel.addAttribute("staffId", theId);

        theModel.addAttribute("sessionWorkHour", theListWorkHourSession);
        return "manage/manage-staff-workHour";
    }


    @GetMapping("/staff/delete/{workHourId}")
    public String deleteWorkHour(@PathVariable("workHourId") int theWorkHourId, Model theModel, HttpServletRequest request){
      WorkHour workHour = multiService.findWorkHourById(theWorkHourId);
      int userId = workHour.getUser().getId();
      multiService.deleteWorkHourById(theWorkHourId);
      request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
      return "redirect:/manage/staff/show/"+userId;
      
    }
}
