package com.night.basecore.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static final String yyyy_MM_dd          = "yyyy-MM-dd";

    public static final String yyyy_MM_dd_number   = "yyyyMMdd";

    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    public static final String CN_Y_M_d            = "yyyy年M月d日";

    public static final String HH_mm_ss            = "HH:mm:ss";

    // get current year
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    // get current month
    public static int getCurrentMouth() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        return (calendar.get(Calendar.MONTH) + 1);
    }

    // get current day
    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    // get current hour
    public static int getCurrentHour() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    // get current minute
    public static int getCurrentMinute() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    // get current second
    public static int getCurrentSecond() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    // get year of date
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    // get month of date
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (calendar.get(Calendar.MONTH) + 1);
    }

    // get day of date
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    // get hour of date
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    // get minute of date
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    // get second of date
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    // str to date
    public static Date str2Date(String dateStr, String format) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    // format current date to str
    public static String getCurrentDate2Str(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    // format date to str
    public static String formatDate2Str(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 得到某个日期之后dayNumber天的格式化日期
     * @param currentDate
     * @param dayNumber
     * @return
     */
    public static String getDateAfter(String currentDate, String format, int dayNumber) {
        Date date = str2Date(currentDate, format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + dayNumber);
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

    /**
     * 得到某个日期之后dayNumber天的格式化日期
     * @param currentDate
     * @param dayNumber
     * @return
     */
    public static String getDateAfter(int currentDate, String format, int dayNumber) {
        return getDateAfter(String.valueOf(currentDate),format,dayNumber);
    }

}
