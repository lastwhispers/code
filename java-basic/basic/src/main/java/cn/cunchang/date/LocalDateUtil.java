package cn.cunchang.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class LocalDateUtil {
    /**
     * yyyy-MM-dd HH:MM:ss
     */
    public final static String yyyyMMddHHMMss = "yyyy-MM-dd HH:MM:ss";

    /**
     * yyyy-MM-dd HH:MM:ss
     */
    public final static String yyyyMMddHHMMssTime = "yyyyMMddHHMMss";

    /**
     * yyyyMMdd
     */
    public final static String yyyyMMdd = "yyyyMMdd";

    /**
     * yyyyMM
     */
    public final static String yyyyMM = "yyyyMM";

    /**
     * yyyyMM
     */
    public final static String yyMM = "yyMM";

    public final static DateTimeFormatter YYYYMMDD_FORMATTER = DateTimeFormatter.ofPattern(yyyyMMdd);


    /**
     * 字符串转日期
     */
    public static LocalDate parseStr(String date, String pattern) {
        if (Objects.isNull(pattern) || Objects.isNull(date)) {
            return null;
        }
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取日期对应的月份数字,
     *
     * @param billDate 格式20200405
     */
    public static Integer getMonth(Integer billDate) {
        if (Objects.isNull(billDate)) {
            return null;
        }
        //月账单时间格式202010
        String billMonth = String.valueOf(billDate).substring(0, 6);
        return Integer.valueOf(billMonth);
    }

    /**
     * 字符串转日期 -yyyyMMdd
     */
    public static LocalDate parse4YYYYMMdd(String date) {
        if (Objects.isNull(date)) {
            return null;
        }
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(yyyyMMdd));
    }

    /**
     * Date转换为LocalDateTime
     */

    public static LocalDateTime convertDateToLDT(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * Date转换为LocalDateTime
     */

    public static LocalDate convertDateToLD(Date date) {
        if (Objects.isNull(date)) {
            return null;
        }
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * LocalDateTime转换为Date
     */
    public static Date convertLDTToDate(LocalDateTime time) {
        if (Objects.isNull(time)) {
            return null;
        }

        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取指定日期的毫秒
     */
    public static Long getMilliByTime(LocalDateTime time) {
        if (Objects.isNull(time)) {
            return null;
        }
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取指定日期的毫秒
     */
    public static Long getMilliByDate(LocalDate date) {
        if (Objects.isNull(date)) {
            return null;
        }
        return getMilliByTime(date.atStartOfDay());
    }

    /**
     * 获取指定时间的指定格式
     */
    public static String formatTime(LocalDateTime time, String pattern) {
        if (Objects.isNull(time) || Objects.isNull(pattern)) {
            return null;
        }
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取指定时间的指定格式,返回格式20201112
     */
    public static Integer formatIntTime(LocalDate time) {
        if (Objects.isNull(time)) {
            return null;
        }
        String date = time.format(YYYYMMDD_FORMATTER);
        return Integer.valueOf(date);
    }

    /**
     * 获取指定时间的指定格式
     */
    public static String formatTime(LocalDate time, String pattern) {
        if (Objects.isNull(time) || Objects.isNull(pattern)) {
            return null;
        }
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }


    /**
     * 获取指定时间的指定格式
     */
    public static String formatTimeYYYYMMDD(LocalDate time) {
        if (Objects.isNull(time)) {
            return null;
        }
        return time.format(YYYYMMDD_FORMATTER);
    }

    /**
     * 获取当前时间的指定格式
     */
    public static String formatNow(String pattern) {
        if (Objects.isNull(pattern)) {
            return null;
        }
        return formatTime(LocalDateTime.now(), pattern);
    }

    /**
     * 获取一天的开始时间，2017,7,22 00:00
     */
    public static LocalDateTime getDayStart(LocalDateTime time) {
        if (Objects.isNull(time)) {
            return null;
        }
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0);
    }

    //获取一天的结束时间，2017,7,22 23:59:59.999999999
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        if (Objects.isNull(time)) {
            return null;
        }
        return time.withHour(23)
                .withMinute(59)
                .withSecond(59);
    }



}
