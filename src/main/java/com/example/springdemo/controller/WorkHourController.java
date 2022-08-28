package com.example.springdemo.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.example.springdemo.SalaryForm;
import com.example.springdemo.WorkHourSession;
import com.example.springdemo.entity.User;
import com.example.springdemo.entity.WorkHour;
import com.example.springdemo.service.MultiService;
import com.example.springdemo.utils.Constant;
import com.example.springdemo.utils.LeaveUtils;
import com.example.springdemo.utils.MyDateUtils;

@Controller
@RequestMapping("/work-hour")
public class WorkHourController {

  @Autowired
  private MultiService multiService;
    
    @GetMapping("")
    public String showWorkHourPage(Model theModel, HttpServletRequest request){

        
    

      UserDetails userdetail = (UserDetails ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      User theUser = multiService.findUserByEmail(userdetail.getUsername());

      Map<String, Float> theLeaves = LeaveUtils.getLeaveOnDay(theUser.getAnnualLeaves());
      SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
      int index = 0;
      long sumHour = 0L;
      List<WorkHour> theList = theUser.getWorkHours();
      List<WorkHourSession> theListWorkHourSession = new ArrayList<>();
      WorkHourSession tempWorkHourSession = new WorkHourSession();

      if(theList.size() ==1){
        tempWorkHourSession.setDateSession(theList.get(0).getStartHour());
        tempWorkHourSession.getSessionWorkHour().add(theList.get(0));
        theListWorkHourSession.add(tempWorkHourSession);
        if(theList.get(0).getEndHour() != null){
          sumHour += (theList.get(0).getEndHour().getTime() - theList.get(0).getStartHour().getTime());
          theListWorkHourSession.get(index).setSumWorkHour(sumHour);
          theListWorkHourSession.get(index).setStringSumWorkHour(MyDateUtils.DateDiffToString(sumHour));
          
          if (sumHour > Constant.EIGHT_HOUR_TO_MILISECOND) {
              tempWorkHourSession.setOverTime(sumHour - Constant.EIGHT_HOUR_TO_MILISECOND);
              tempWorkHourSession.setStringOverTime(MyDateUtils.DateDiffToString(tempWorkHourSession.getOverTime()));
            } else {
              tempWorkHourSession.setOverTime(0);
              tempWorkHourSession.setStringOverTime(MyDateUtils.DateDiffToString(tempWorkHourSession.getOverTime()));
            }
            tempWorkHourSession.setLeave(0.0f);
            for(Entry<String, Float> entry: theLeaves.entrySet()){
              
              if(entry.getKey().equals(MyDateUtils.DateToString(tempWorkHourSession.getDateSession()))){
                  
                  tempWorkHourSession.setLeave(entry.getValue());
                  // System.out.println(tempWorkHourSession.getLeave());
              }
            }
        }

          
        }else if(theList.size() >=2){
          tempWorkHourSession.setDateSession(theList.get(0).getStartHour());
          theListWorkHourSession.add(tempWorkHourSession);
          for(int i =0 ; i< theList.size(); i++){

            theListWorkHourSession.get(index).getSessionWorkHour().add(theList.get(i));
            if(theList.get(i).getEndHour() != null){
                
                sumHour += (theList.get(i).getEndHour().getTime() - theList.get(i).getStartHour().getTime());
                
            }
            

            // System.out.println(fm1.format(theList.get(i).getStartHour()) + " ;" + fm1.format(theList.get(i+1).getStartHour()));
            if(!fm1.format(theList.get(i).getStartHour()).equals(fm1.format(theList.get(i+1).getStartHour()))){
                
                tempWorkHourSession.setSumWorkHour(sumHour);
                tempWorkHourSession.setStringSumWorkHour(MyDateUtils.DateDiffToString(sumHour));
                if (sumHour > Constant.EIGHT_HOUR_TO_MILISECOND) {
                    tempWorkHourSession.setOverTime(sumHour - Constant.EIGHT_HOUR_TO_MILISECOND);
                    tempWorkHourSession.setStringOverTime(MyDateUtils.DateDiffToString(tempWorkHourSession.getOverTime()));
                  } else {
                    tempWorkHourSession.setOverTime(0);
                    tempWorkHourSession.setStringOverTime(MyDateUtils.DateDiffToString(tempWorkHourSession.getOverTime()));
                  }
                  tempWorkHourSession.setLeave(0.0f);
                  for(Entry<String, Float> entry: theLeaves.entrySet()){
                    
                    if(entry.getKey().equals(MyDateUtils.DateToString(tempWorkHourSession.getDateSession()))){
                        
                        tempWorkHourSession.setLeave(entry.getValue());
                        // System.out.println(tempWorkHourSession.getLeave());
                    }
                  }

                  
                
                tempWorkHourSession = new WorkHourSession();
                tempWorkHourSession.setDateSession(theList.get(i+1).getStartHour());

                theListWorkHourSession.add(tempWorkHourSession);
                index++;
                sumHour = 0L;

            }

            if(i == theList.size() -2){
                theListWorkHourSession.get(index).getSessionWorkHour().add(theList.get(i+1));
                if(theList.get(i+1).getEndHour() != null){
                    sumHour += (theList.get(i+1).getEndHour().getTime() - theList.get(i+1).getStartHour().getTime());
                    theListWorkHourSession.get(index).setSumWorkHour(sumHour);
                    theListWorkHourSession.get(index).setStringSumWorkHour(MyDateUtils.DateDiffToString(sumHour));
                    
                    if (sumHour > Constant.EIGHT_HOUR_TO_MILISECOND) {
                        tempWorkHourSession.setOverTime(sumHour - Constant.EIGHT_HOUR_TO_MILISECOND);
                        tempWorkHourSession.setStringOverTime(MyDateUtils.DateDiffToString(tempWorkHourSession.getOverTime()));
                      } else {
                        tempWorkHourSession.setOverTime(0);
                        tempWorkHourSession.setStringOverTime(MyDateUtils.DateDiffToString(tempWorkHourSession.getOverTime()));
                      }
                      tempWorkHourSession.setLeave(0.0f);
                      for(Entry<String, Float> entry: theLeaves.entrySet()){
                        
                        if(entry.getKey().equals(MyDateUtils.DateToString(tempWorkHourSession.getDateSession()))){
                            
                            tempWorkHourSession.setLeave(entry.getValue());
                            // System.out.println(tempWorkHourSession.getLeave());
                        }
                      }
                }
                break;
            }
            

          }
        }
        
        

    

        // System.out.println(theLeaves);
        // System.out.println(theListWorkHourSession);
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
      System.out.println(principal.getName()); 
      theModel.addAttribute("pageTitle", "Thông tin lương");
      theModel.addAttribute("path", "/work-hour");
      
      return "work-hour/salary";
    }
}
