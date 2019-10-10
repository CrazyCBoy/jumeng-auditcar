package com.jumeng.auditcar.common;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * @version 1.0 
 * 
 * @author 作者姓名 
 * 
 * 创建时间：2012-6-7 下午04:30:35 
 * 
 * 类说明 
 */
public class DateStrUtil {
    public static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddHHmmss");
    static SimpleDateFormat format3 = new SimpleDateFormat("yyyyMMdd");
    static SimpleDateFormat format4sql = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat format5sql = new SimpleDateFormat("yyyy-MM");
    static SimpleDateFormat year = new SimpleDateFormat("yyyy");
    static SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
    public static String date2str(Date date){
        if(date!=null){
            return format.format(date); 
        }else{
            return null;
        }
        
    }
    
    public static Date str2date(String str) throws ParseException{
        if(!("".equals(str.trim()))&&str!=null){
            return format.parse(str); 
        }else{
            return null;
        }
        
    }
    
    public static String date3str(Date date){
        if(date!=null){
            return format3.format(date); 
        }else{
            return null;
        }
        
    }
    
    public static Date str2date2(String str) throws ParseException{
        if(!("".equals(str.trim()))&&str!=null){
            return format2.parse(str); 
        }else{
            return null;
        }
        
    }

    public static String date2str2(Date date) throws ParseException{
        if(date!=null){
            return format2.format(date); 
        }else{
            return null;
        }
        
    }
    
    public static Date str2date4sql(String str) throws ParseException{
        if(!("".equals(str.trim()))&&str!=null){
            return format4sql.parse(str); 
        }else{
            return null;
        }
        
    }
    
    public static String date2str4sql(Date date){
        if(date!=null){
            return format4sql.format(date); 
        }else{
            return null;
        }
        
    }

    public static Date str2date5sql(String str) throws ParseException{
    	if(!("".equals(str.trim()))&&str!=null){
    		return format5sql.parse(str); 
    	}else{
    		return null;
    	}
    	
    }
    
    public static String date2str5sql(Date date){
    	if(date!=null){
    		return format5sql.format(date); 
    	}else{
    		return null;
    	}
    	
    }
    
    public static String date2strYear(Date date){
        if(date!=null){
            return year.format(date); 
        }else{
            return null;
        }
    }
    
    public static String date2Time(Date date){
        if(date!=null){
            return time.format(date); 
        }else{
            return null;
        }
    }
    /**
     * 传入日期  返回 当天的 0点0分0秒
     * @param date
     * @return
     */
    public static Date getBeginDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 传入日期  返回 当天的 23点59分59秒
     * @param date
     * @return
     */
    public static Date getEndDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
    
    /**
     * 传入日期  返回 当前周第一天的 0点0分0秒
     * @param date
     * @return
     */
    public static Date getWeekBeginDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 传入日期  返回 当前周最后一天的 23点59分59秒
     * @param date
     * @return
     */
    public static Date getWeekEndDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);  
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
    
    /**
     * 传入日期  返回 当天的 0点0分0秒
     * @param date
     * @return
     */
    public static Date getMounthBeginDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);  
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 传入日期  返回 当天的 23点59分59秒
     * @param date
     * @return
     */
    public static Date getMounthEndDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);  
        calendar.set(Calendar.DAY_OF_MONTH, 0);  
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 传入日期   返回当年第一天的零点
     * @param date
     * @return
     */
    public static Date getYearBeginDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 0);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 传入日期   返回当年最后一天的23:59:59
     * @param date
     * @return
     */
    public static Date getYearEndDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        calendar.set(Calendar.DAY_OF_YEAR,0);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
    
    /**
     * 获取day天之前的日期
     * @param day
     * @return
     */
    public static Date getDayBefore(Integer day){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0 - day);  
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    
    /**
     * 指定日期加上天数后的日期
     * @param num 为增加的天数
     * @param newDate 创建时间
     * @return
     * @throws ParseException 
     */
    public static String plusDay(int num,String newDate) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date  currdate = format.parse(newDate);
        System.out.println("现在的日期是：" + currdate);
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        currdate = ca.getTime();
        String enddate = format.format(currdate);
        System.out.println("增加天数以后的日期：" + enddate);
        return enddate;
    }
    
    
    //当前日期加上天数：


    /**
     * 当前日期加上天数后的日期
     * @param num 为增加的天数
     * @return
     */
    public static Date plusDay2(int num){
        Calendar ca = Calendar.getInstance();
        System.out.println("现在的日期是：" + date2str(ca.getTime()));
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        Date endDate = ca.getTime();
        System.out.println("增加天数以后的日期：" + date2str(endDate));
        return endDate;
    }
    
    /**
     * 当前时间加上分钟
     * @param num 为增加的分钟数
     * @return
     */
    public static Date plusMinute(int num){
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.MINUTE, num);
        return ca.getTime();
    }

    /**
     * 当前时间加上分钟
     * @param num 为增加的分钟数
     * @return
     */
    public static Date plusSecond(int num){
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.SECOND, num);
        return ca.getTime();
    }
    
    /**
     * <p>Created on 2017年10月12日</p>
     * <p>Description:[将一天划分为48个时间段]</p>
     * @author:[谭磊][tanlei@tingzhijun.com]
     * @update:[日期YYYY-MM-DD] [更改人姓名]
     * @return Integer
     */
    public static Integer timeTo48(String datetime)
    {
        String time = datetime.substring(11);
        String hour = time.substring(0,2);
        String minitue = time.substring(3,5);
        int timeIndex = Integer.parseInt(hour)*2+1;
        if(Integer.parseInt(minitue)-30>=0)
        {
            timeIndex ++;
        }
        return timeIndex;
    }
    
    public static Integer dayToWeek(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        Integer week = ca.get(Calendar.DAY_OF_WEEK) - 1;
        if(week == 0) {
            week = 7;
        }
        return week;
    }

    public static Integer dayToMonth(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.DAY_OF_MONTH);
    }
    
    public static int differentDaysByMillisecond(Date date1,Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }
    
    public static int differentMinuteByMillisecond(Date date1,Date date2) {
        int minutes = (int) ((date2.getTime() - date1.getTime()) / (1000*60));
        return minutes;
    }
    
    public static Boolean dateIsValidity(Date date1, Date date2) throws Exception {
        Date now = new Date();
        if(date1.getTime() <= now.getTime() && date2.getTime() >= now.getTime()) {
            return true;
        }
        return false;
    }
    
    public static Boolean timeIsValidity(String time1, String time2) throws Exception {
        Date now = new Date();
        String beginTimeStr = date2str4sql(now) + " " + time1;
        String endTimeStr = date2str4sql(now) + " " + time2;
        Date beginDate = str2date4sql(beginTimeStr);
        Date endDate = str2date4sql(endTimeStr);
        if(beginDate.getTime() <= now.getTime() && endDate.getTime() >= now.getTime()) {
            return true;
        }
        return false;
    }
    
    public static Boolean isInTime(String time, String time1, String time2) throws Exception {
        Date now = new Date();
        String nowTimeStr = date2str4sql(now) + " " + time;
        String beginTimeStr = date2str4sql(now) + " " + time1;
        String endTimeStr = date2str4sql(now) + " " + time2;
        Date nowDate = str2date(nowTimeStr);
        Date beginDate = str2date(beginTimeStr);
        Date endDate = str2date(endTimeStr);
        if(beginDate.getTime() <= nowDate.getTime() && endDate.getTime() >= nowDate.getTime()) {
            return true;
        }
        return false;
    }
    
    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @param format
     * @return
     */
    public static String timeStamp2Date(String seconds,String format) {
        if(seconds == null || StringUtils.isEmpty(seconds) || seconds.equals("null")){
            return "";
        }
        if(format == null || StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds+"000")));
    }
    
    public static void main(String[] args) throws ParseException {
//      System.out.println(str2date2("20130411155930"));
        
//      String ss = "10:02:30";
//      SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//      Date date = sdf.parse(ss);
        
//      Date date = new Date();
//      Date wb = getWeekBeginDate(date);
//      Date we = getWeekEndDate(date);
//      Date mb = getMounthBeginDate(date);
//      Date me = getMounthEndDate(date);
//      
//      SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//      System.out.println(sdf1.format(wb));
//      System.out.println(sdf1.format(we));
//      System.out.println(sdf1.format(mb));
//      System.out.println(sdf1.format(me));
        
//      System.out.println(timeTo48("2017-10-11 08:00:00"));
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, 11);
        System.out.println(dayToWeek(ca.getTime()));
        System.out.println(dayToMonth(ca.getTime()));
    }
}
 
