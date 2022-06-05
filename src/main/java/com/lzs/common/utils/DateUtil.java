package com.lzs.common.utils;

import org.apache.logging.log4j.util.Strings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final String DATE_PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取今天最开始的时间（0点）
     * @return
     */
    public static Date getTodayStartTime() {
        return getDateStartTime(getNowDate());
    }

    /**
     * 获取日期当天最开始的时间（0点）
     * @param date
     * @return
     */
    public static Date getDateStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 将日期转换为字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (Strings.isBlank(pattern)) {
            pattern = DATE_PATTERN_DEFAULT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Long dateConvertLong(Date date) {
        return date.getTime();
    }

    public static Date parse(String dateStr, String pattern) {
        if (Strings.isBlank(pattern)) {
            pattern = DATE_PATTERN_DEFAULT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前时间的前min分钟的时间
     * @param min
     * @return
     */
    public static Date getNowTimeBeforeMinute(int min) {
        return getTimeBeforeMinute(getNowDate(), min);
    }

    /**
     * 获取指定时间前n分钟
     * @param date
     * @param min
     * @return
     */
    public static Date getTimeBeforeMinute(Date date, int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, min*(-1));
        return calendar.getTime();
    }

    /**
     * 获取前n天的时间（24h*n之前）
     * @param date 日期对象
     * @return
     */
    public static Date getDayBefore(Date date, int n){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - n);
        return calendar.getTime();
    }

    /**
     * 获取前一天的时间（24h之前）
     * @param date 日期对象
     * @return
     */
    public static Date getOneDayBefore(Date date){
        return getDayBefore(date, 1);
    }

    /**
     * 获取当前系统时间前24h的时间
     * @return
     */
    public static Date getOneDayBeforeNow() {
        return getOneDayBefore(getNowDate());
    }

    /**
     * 获取当前系统时间
     * @return
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 根据时间戳获取日期对象
     * @param timestamp 时间戳（毫秒）
     * @return
     */
    public static Date getDateByTimestamp(Long timestamp) {
        return new Date(timestamp);
    }

    /**
     * 2020-07-23T16:00:00.000Z 时间戳格式化
     * @param date
     * @return
     */
    public static Date format(String date) {
        date = date.replace("Z", " UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss Z");
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算Date相差天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDayDiffer(Date startDate, Date endDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long startDateTime = 0;
        long endDateTime = 0;
        try {
            startDateTime = dateFormat.parse(dateFormat.format(startDate)).getTime();
            endDateTime = dateFormat.parse(dateFormat.format(endDate)).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int) ((endDateTime - startDateTime) / (1000 * 3600 * 24));
    }

    public static void main(String[] args) {
        System.out.println(format("2021-12-13T12:16:39Z"));
    }
}
