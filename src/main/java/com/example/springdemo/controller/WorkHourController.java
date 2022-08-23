package com.example.springdemo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springdemo.WorkHourSession;
import com.example.springdemo.entity.User;
import com.example.springdemo.entity.WorkHour;

@Controller
@RequestMapping("/work-hour")
public class WorkHourController {
    
    @GetMapping("")
    public String showWorkHourPage(Model theModel, HttpServletRequest request){

        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
    

        User theUser = (User )request.getAttribute("user");
        int index = 0;
        long sumHour = 0L;
        List<WorkHour> theList = theUser.getWorkHours();
        List<WorkHourSession> theListWorkHourSession = new ArrayList<>();
        WorkHourSession tempWorkHourSession = new WorkHourSession();
        tempWorkHourSession.setDateSession(theList.get(0).getStartHour());
        theListWorkHourSession.add(tempWorkHourSession);
        for(int i =0 ; i< theList.size(); i++){

            theListWorkHourSession.get(index).getSessionWorkHour().add(theList.get(i));
            if(theList.get(i).getEndHour() != null){
                System.out.println(theList.get(i).getDateDiff().getTime()+ ";" + theList.get(i).getDateDiff());
                sumHour += (theList.get(i).getEndHour().getTime() - theList.get(i).getStartHour().getTime());
            }
            

            System.out.println(fm1.format(theList.get(i).getStartHour()) + " ;" + fm1.format(theList.get(i+1).getStartHour()));
            if(!fm1.format(theList.get(i).getStartHour()).equals(fm1.format(theList.get(i+1).getStartHour()))){
                
                tempWorkHourSession.setSumWorkHour(new Date(sumHour));
                System.out.println(sumHour);
                
                tempWorkHourSession = new WorkHourSession();
                tempWorkHourSession.setDateSession(theList.get(i+1).getStartHour());

                theListWorkHourSession.add(tempWorkHourSession);
                index++;
                sumHour = 0L;

            }

            if(i == theList.size() -2){
                theListWorkHourSession.get(index).getSessionWorkHour().add(theList.get(i+1));
                if(theList.get(i+1).getEndHour() != null){
                    sumHour += (theList.get(i).getEndHour().getTime() - theList.get(i).getStartHour().getTime());
                    theListWorkHourSession.get(index).setSumWorkHour(new Date(sumHour));
                    System.out.println(sumHour);
                }
                break;
            }
            

        }

        System.out.println(theListWorkHourSession.size());
        theModel.addAttribute("user", theUser);
        theModel.addAttribute("sessionWorkHour", theListWorkHourSession);
        return "work-hour/work-hour";
    }
}
