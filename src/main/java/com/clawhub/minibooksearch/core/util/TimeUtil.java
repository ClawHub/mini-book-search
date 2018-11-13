package com.clawhub.minibooksearch.core.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * <Description> 时间工具类<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2018-10-16 22:53<br>
 */
public class TimeUtil {
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String BASIC_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * String to milli long.
     *
     * @param text    the text
     * @param pattern the pattern
     * @return the long
     */
    public static long stringToMilli(String text, String pattern) {
        LocalDateTime dateTime = LocalDateTime.parse(text, DateTimeFormatter.ofPattern(pattern));
        return dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 获取当前系统时间，格式为mmddhhmmss
     * @return
     */
    public static String currentDateTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddHHmmss");
        return simpleDateFormat.format(date);
    }

}
