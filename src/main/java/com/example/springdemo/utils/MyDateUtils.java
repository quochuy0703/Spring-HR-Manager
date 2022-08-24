package com.example.springdemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateUtils {
    public static String DateDiffToString(long diff){
        
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;

        return String.format("%02d", diffHours)+":"+ String.format("%02d", diffMinutes)+":"+String.format("%02d", diffSeconds);
    }

    public static String DateToString(Date theDate){
        SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
        return fm1.format(theDate);
    }
}
