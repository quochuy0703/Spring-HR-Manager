package com.example.springdemo.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import com.example.springdemo.entity.AnnualLeave;

public class LeaveUtils {
    public static float getDay(Date date, AnnualLeave leave) {
        // console.log(date, leave.startDateLeave, leave.endDateLeave);
        if (
          !MyDateUtils.DateToString(date).equals(MyDateUtils.DateToString(leave.getStartDate())) &&
          !MyDateUtils.DateToString(date).equals(MyDateUtils.DateToString(leave.getEndDate()))
        ) {
          return 1;
        } else if (MyDateUtils.DateToString(date).equals(MyDateUtils.DateToString(leave.getStartDate()))) {
          if (leave.isMorningStartDate()) {
            return 1;
          } else {
            return 0.5f;
          }
        } else {
          if (leave.isMorningEndDate()) {
            return 0;
          } else {
            return 0.5f;
          }
        }
      };

      public static Date plusOneDate(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        return c.getTime();
      }

      public static HashMap<String, Float> getLeaveOnDay(List<AnnualLeave> theLists){
        HashMap<String, Float> map = new HashMap<>();
        for(AnnualLeave leave : theLists){
         
            for(Date d= leave.getStartDate(); 
            d.before(leave.getEndDate()) || d.equals(leave.getEndDate()); 
            d= plusOneDate(d) ){

              float count = getDay(d, leave);
              
              if(count !=0){
                map.put(MyDateUtils.DateToString(d), count);
              }

            }
        }
        return map;
      }


      public static HashMap<String, Float> getLeaveOnDayByMonth(List<AnnualLeave> theLists, String month, String year){
        HashMap<String, Float> map = new HashMap<>();
        for(AnnualLeave leave : theLists){
         
            for(Date d= leave.getStartDate(); 
            d.before(leave.getEndDate()) || d.equals(leave.getEndDate()); 
            d= plusOneDate(d) ){

              float count = getDay(d, leave);
           
              
              if(count !=0 && d.getMonth()+1 == Integer.parseInt(month)){
                map.put(MyDateUtils.DateToString(d), count);
              }

            }
        }
        return map;
      }
}
