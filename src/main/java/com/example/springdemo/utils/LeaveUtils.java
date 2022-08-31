package com.example.springdemo.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.aspectj.weaver.patterns.IfPointcut.IfFalsePointcut;

import com.example.springdemo.entity.AnnualLeave;

public class LeaveUtils {


  //tính toán số ngày nghỉ phép
    public static float getCountLeave(AnnualLeave annualLeave){
       
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

       return countDay;
    }

    //neu 0.6f thi nghi nua buoi chieu, 0.5f thi nghi nua buoi sang; 1.0f nghi ca ngay
    public static float getDay(Date date, AnnualLeave leave) {
        // console.log(date, leave.startDateLeave, leave.endDateLeave);
        if (
          !MyDateUtils.DateToString(date).equals(MyDateUtils.DateToString(leave.getStartDate())) &&
          !MyDateUtils.DateToString(date).equals(MyDateUtils.DateToString(leave.getEndDate()))
        ) {
          return 1;
        } else if(
          MyDateUtils.DateToString(date).equals(MyDateUtils.DateToString(leave.getStartDate())) &&
          MyDateUtils.DateToString(date).equals(MyDateUtils.DateToString(leave.getEndDate()))
        ){
          if(leave.isMorningStartDate()){
            return 0.5f;
          }else{
            return 0.6f;
          }
        }else if (MyDateUtils.DateToString(date).equals(MyDateUtils.DateToString(leave.getStartDate()))) {
          if (leave.isMorningStartDate()) {
            return 1;
          } else {
            return 0.6f;
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


      
      public static HashMap<String, Float> getLeaveOnDay(List<AnnualLeave> theLists, boolean isFlagMorning){
        HashMap<String, Float> map = new HashMap<>();
        for(AnnualLeave leave : theLists){
         
            for(Date d= leave.getStartDate(); 
            d.before(leave.getEndDate()) || d.equals(leave.getEndDate()); 
            d= plusOneDate(d) ){

              float count = getDay(d, leave);
              
              if(count !=0){
                if(!isFlagMorning){
                  if(count == 0.6f){
                    count = 0.5f;
                  }
                }
                for(Entry<String, Float> entry: map.entrySet()){
                  if(entry.getKey().equals(MyDateUtils.DateToString(d))){
                    if(entry.getValue().equals(0.5f) || entry.getValue().equals(0.6f)){
                      count = 1.0f;
                      break;
                    }
                  }
                }
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

      public static boolean checkSameLeave(Map<String, Float> listTempAnnualLeave, Map<String, Float> listAnnualLeave){
        for(Entry<String, Float> entry : listAnnualLeave.entrySet() ){
            for(Entry<String, Float> entryTemp : listTempAnnualLeave.entrySet() ){
                if(entry.getKey().equals(entryTemp.getKey())){
                    if(entry.getValue() == 1.0f){
                        return true;
                    }
                    if(entry.getValue().equals(entryTemp.getValue())){
                        return true;
                    }
                }
            }
        }
        return false;
      }
      
}
