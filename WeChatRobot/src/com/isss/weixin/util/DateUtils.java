package com.isss.weixin.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class DateUtils {


    public static String deaultDateFormat = "yyyy-MM-dd";
    public static String deaultDateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYYMM = "yyyyMM";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String YYYY_MM_DD_CN = "yyyy年MM月dd";
    public static final String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";


    /**
     * 格式化日期，返回固定格式
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return returnValue;
    }

    public static String getCurrentDateStr(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(deaultDateFormat);
        return simpleDateFormat.format(getCurrentDate());
    }    public static String getCurrentDateStrYYYYMMDDHHMMSS(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYYMMDDHHMMSS);
        return simpleDateFormat.format(getCurrentDate());
    }

    public static String getCurrentDateTimeStr(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(deaultDateTimeFormat);
        return simpleDateFormat.format(getCurrentDate());
    }
    public static String getCurrentDateTimeStr2(long s){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(deaultDateTimeFormat);
        return simpleDateFormat.format(parseDateTime(s));
    }
    public static Date getCurrentDate(){
        return new Date();
    }

    public static int getCurrentYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getCurrentMonth(){
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static int getCurrentDay(){
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static int getCurrentMaxDay(){
        return Calendar.getInstance().getMaximum(Calendar.DAY_OF_MONTH);
    }

    public static Date parseDateTime(String str){
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(deaultDateTimeFormat);
       
            try {
				date = simpleDateFormat.parse(str);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     
        return date;
    }

    public static Date parseDateTime(long s){
        return new Date(s);
    }

    public static int getHour(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    public static int getMinute(long s){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDateTime(s));
        return calendar.get(Calendar.MINUTE);
    }

    public static int second2Minute(long s){
        return (int) (s/1000/60);
    }
    public static boolean Is30Minute(String time) {
    	if(Math.abs(getCurrentDate().getTime()-parseDateTime(time).getTime())>30*60*1000) {
    		return false;
    	}else {
    		return true;
    	}	
    }
}
