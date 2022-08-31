package com.example.springdemo.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.example.springdemo.WorkHourSession;
import com.example.springdemo.entity.WorkHour;

public class WorkHourUtils {
    public static List<WorkHourSession> getSessionWorkHour(List<WorkHour> theList, Map<String, Float> theLeaves){

        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
        int index = 0;
        long sumHour = 0L;
        
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

            tempWorkHourSession.setLeave(0.0f);
            for(Entry<String, Float> entry: theLeaves.entrySet()){
            
                if(entry.getKey().equals(MyDateUtils.DateToString(tempWorkHourSession.getDateSession()))){
                    
                    tempWorkHourSession.setLeave(entry.getValue());
                    // System.out.println(tempWorkHourSession.getLeave());
                }
            }

            if (sumHour > Constant.EIGHT_HOUR_TO_MILISECOND) {
                tempWorkHourSession.setOverTime(sumHour - Constant.EIGHT_HOUR_TO_MILISECOND);
                tempWorkHourSession.setStringOverTime(MyDateUtils.DateDiffToString(tempWorkHourSession.getOverTime()));
                tempWorkHourSession.setMissTime(0);
            } else {
                tempWorkHourSession.setOverTime(0);
                tempWorkHourSession.setStringOverTime(MyDateUtils.DateDiffToString(tempWorkHourSession.getOverTime()));
                if (sumHour + tempWorkHourSession.getLeave() *8 * Constant.ONE_HOUR_TO_MILISECOND >
                    Constant.EIGHT_HOUR_TO_MILISECOND) {
                        tempWorkHourSession.setMissTime(0);
                    } else {
                        tempWorkHourSession.setMissTime(Constant.EIGHT_HOUR_TO_MILISECOND -
                        (sumHour + (int)(tempWorkHourSession.getLeave() * 8 )* Constant.ONE_HOUR_TO_MILISECOND));
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

                tempWorkHourSession.setLeave(0.0f);
                  for(Entry<String, Float> entry: theLeaves.entrySet()){
                    
                    if(entry.getKey().equals(MyDateUtils.DateToString(tempWorkHourSession.getDateSession()))){
                        
                        tempWorkHourSession.setLeave(entry.getValue());
                        // System.out.println(tempWorkHourSession.getLeave());
                    }
                  }


                  if (sumHour > Constant.EIGHT_HOUR_TO_MILISECOND) {
                    tempWorkHourSession.setOverTime(sumHour - Constant.EIGHT_HOUR_TO_MILISECOND);
                    tempWorkHourSession.setStringOverTime(MyDateUtils.DateDiffToString(tempWorkHourSession.getOverTime()));
                    tempWorkHourSession.setMissTime(0);
                } else {
                    tempWorkHourSession.setOverTime(0);
                    tempWorkHourSession.setStringOverTime(MyDateUtils.DateDiffToString(tempWorkHourSession.getOverTime()));
                    if (sumHour + tempWorkHourSession.getLeave() *8 * Constant.ONE_HOUR_TO_MILISECOND >
                        Constant.EIGHT_HOUR_TO_MILISECOND) {
                            tempWorkHourSession.setMissTime(0);
                        } else {
                            tempWorkHourSession.setMissTime(Constant.EIGHT_HOUR_TO_MILISECOND -
                            (sumHour + (int)(tempWorkHourSession.getLeave() * 8 )* Constant.ONE_HOUR_TO_MILISECOND));
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
                    
                    tempWorkHourSession.setLeave(0.0f);
                      for(Entry<String, Float> entry: theLeaves.entrySet()){
                        
                        if(entry.getKey().equals(MyDateUtils.DateToString(tempWorkHourSession.getDateSession()))){
                            
                            tempWorkHourSession.setLeave(entry.getValue());
                            // System.out.println(tempWorkHourSession.getLeave());
                        }
                      }

                      if (sumHour > Constant.EIGHT_HOUR_TO_MILISECOND) {
                        tempWorkHourSession.setOverTime(sumHour - Constant.EIGHT_HOUR_TO_MILISECOND);
                        tempWorkHourSession.setStringOverTime(MyDateUtils.DateDiffToString(tempWorkHourSession.getOverTime()));
                        tempWorkHourSession.setMissTime(0);
                    } else {
                        tempWorkHourSession.setOverTime(0);
                        tempWorkHourSession.setStringOverTime(MyDateUtils.DateDiffToString(tempWorkHourSession.getOverTime()));
                        if (sumHour + tempWorkHourSession.getLeave() *8 * Constant.ONE_HOUR_TO_MILISECOND >
                            Constant.EIGHT_HOUR_TO_MILISECOND) {
                                tempWorkHourSession.setMissTime(0);
                            } else {
                                tempWorkHourSession.setMissTime(Constant.EIGHT_HOUR_TO_MILISECOND -
                                (sumHour + (int)(tempWorkHourSession.getLeave() * 8 )* Constant.ONE_HOUR_TO_MILISECOND));
                            }
                    }
                      
                }
                break;
            }
            

          }
        }

        return theListWorkHourSession;
    }

    public static double getSalary(List<WorkHourSession> theListWorkHourSession, float salaryScale){

        long sumHour = 0L;

        for(WorkHourSession workHourSession : theListWorkHourSession){
            sumHour = sumHour + (workHourSession.getOverTime() - workHourSession.getMissTime());
        }

        double salary = salaryScale * 3000000 + (sumHour / Constant.ONE_HOUR_TO_MILISECOND) * 200000;

        return salary;
    }
}
