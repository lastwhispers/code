package cn.cunchang.date;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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


    /**
     * 按步长分割日期
     * <p>
     * 20210101~20210630 =》
     * [2021-01-01=2021-01-31, 2021-02-01=2021-02-28, 2021-03-01=2021-03-31,
     * 2021-04-01=2021-04-30, 2021-05-01=2021-05-31, 2021-06-01=2021-06-30,
     * 2021-07-01=2021-07-31, 2021-08-01=2021-08-31, 2021-09-01=2021-09-30,
     * 2021-10-01=2021-10-31, 2021-11-01=2021-11-30, 2021-12-01=2021-12-31,
     * 2022-01-01=2022-01-31, 2022-02-01=2022-02-28, 2022-03-01=2022-03-31,
     * 2022-04-01=2022-04-30, 2022-05-01=2022-05-31, 2022-06-01=2022-06-30,
     * 2022-07-01=2022-07-31, 2022-08-01=2022-08-31, 2022-09-01=2022-09-30,
     * 2022-10-01=2022-10-31, 2022-11-01=2022-11-30, 2022-12-01=2022-12-31]
     * ---------------
     * 20210101~20210630 =》
     * [2021-01-01=2021-03-31, 2021-04-01=2021-06-30, 2021-07-01=2021-09-30, 2021-10-01=2021-12-31,
     * 2022-01-01=2022-03-31, 2022-04-01=2022-06-30, 2022-07-01=2022-09-30, 2022-10-01=2022-12-31]
     * ---------------
     * 20210101~20210630 =》
     * [2021-01-01=2021-06-30, 2021-07-01=2021-12-31,
     * 2022-01-01=2022-06-30, 2022-07-01=2022-12-31]
     * ---------------
     * 20210101~20210630 =》
     * [2021-01-01=2021-12-31, 2022-01-01=2022-12-31]
     *
     * @param startDate 20210101
     * @param endDate   20210630
     * @param step      步长 {@link so.dian.olympic.common.enums.AggregateTypeEnum}
     * @return <起始日期,结束日期>
     */
    public static List<Pair<LocalDate, LocalDate>> splitByStep(LocalDate startDate, LocalDate endDate, int step) {
        LocalDate firstDayOfMonth = startDate.with(TemporalAdjusters.firstDayOfMonth());
        if(!Objects.equals(startDate, firstDayOfMonth)){
            throw new IllegalArgumentException(String.format("起始日期必须是月份第一天,startDate:%s", startDate));
        }

        LocalDate lastDayOfMonth = endDate.with(TemporalAdjusters.lastDayOfMonth());
        if(!Objects.equals(endDate, lastDayOfMonth)){
            throw new IllegalArgumentException(String.format("结束日期必须是月份最后一天,endDate:%s", endDate));
        }

        List<Pair<LocalDate, LocalDate>> list = new ArrayList<>();

        while (startDate.isBefore(endDate)) {
            // 下一个步长减1的末尾日期
            LocalDate nextStartDate = startDate.plusMonths(step - 1).with(TemporalAdjusters.lastDayOfMonth());
            list.add(new Pair<>(startDate, nextStartDate));
            // 下一个月的起始日期
            startDate = nextStartDate.plusDays(1L);
        }

        return list;
    }

    public static String format(LocalDate localDate, AggregateTypeEnum aggregateTypeEnum) {
        String topCategoryName = null;
        switch (aggregateTypeEnum) {
            case 月:
                topCategoryName = localDate.getYear() + "年" + localDate.getMonthValue() + "月";
                break;
            case 季:
                int quarter = localDate.get(IsoFields.QUARTER_OF_YEAR);
                topCategoryName = localDate.getYear() + "年" + "Q" + quarter;
                break;
            case 半年:
                quarter = localDate.get(IsoFields.QUARTER_OF_YEAR);
                if (quarter <= 2) {
                    topCategoryName = localDate.getYear() + "年" + "上半年";
                }
                if (quarter >= 3) {
                    topCategoryName = localDate.getYear() + "年" + "下半年";
                }
                break;
            case 年:
                topCategoryName = localDate.getYear() + "年";
                break;
            default:

        }
        return topCategoryName;
    }


}
