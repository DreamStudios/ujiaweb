package com.blueshit.joke.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理工具类
 * @author 9527
 * @version 1.0
 * @email zhaohuaan0925@163.com
 * @created 2014/12/12 11:10
 * @description
 */
public class DateUtils {

    /**
     * 获取当天日期（年，月，日）
     */
    public static String getIntradayDate(String partten){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("".equals(partten) ? Constants.DatePartten.LINK_DATE : partten);
        return dateFormat.format(date);
    }

    /**
     * 获取当天日期和当前时间（年，月，日，时，分，秒）
     */
    public static String getIntradayDateAndTime(String partten){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("".equals(partten) ? Constants.DatePartten.LINK_DATE_TIME : partten);
        return dateFormat.format(date);
    }

    /**
     * 给定日期与当前日期间隔
     * @param t
     * @return
     */
    public static int diffDate(long t){
        Calendar cal =Calendar.getInstance();
        cal.setTimeInMillis(t);
        return Calendar.getInstance().get(Calendar.DAY_OF_YEAR)-cal.get(Calendar.DAY_OF_YEAR);
    }
}
