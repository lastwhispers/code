package cn.lastwhisper.feature8.date;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * 以前的时间API是线程不安全的，多线程对日期进行处理要加锁
 *
 * LocalDate、LocalTime、LocalDateTime 类的实例是不可变的对象，分别表示使用 ISO-8601日历系统的日期、时间、日期和时间。
 * 它们提供了简单的日期或时间，并不包含当前的时间信息。也不包含与时区相关的信息。
 */
public class TestLocalDateTime {

    //1.LocalDate    LocalTime   LocalDateTime  用法一样,用于让人读的时间日期
    @Test
    public void test1() {
        //获取当前日期时间
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        //指定一个日期时间
        LocalDateTime ldt2 = LocalDateTime.of(2015, 10, 19, 13, 22, 33);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.plusYears(2);//加2年
        System.out.println(ldt3);

        LocalDateTime ldt4 = ldt.minusMonths(2);//减2个月
        System.out.println(ldt4);

        System.out.println(ldt.getYear());
        System.out.println(ldt.getMonthValue());
        System.out.println(ldt.getDayOfMonth());
        System.out.println(ldt.getHour());
        System.out.println(ldt.getMinute());
        System.out.println(ldt.getSecond());

    }

    //2.Instant:时间戳（以Unix元年： 1970年1月1日00:00:00到某个时间之间的毫秒值）
    @Test
    public void test2() {
        Instant ins1 = Instant.now();//默认获取UTC时区的时间
        System.out.println(ins1);

        OffsetDateTime odt = ins1.atOffset(ZoneOffset.ofHours(8));//获取偏移日期时间，加8小时偏移
        System.out.println(odt);

        System.out.println(ins1.toEpochMilli());//获取与Unix元年间隔毫秒数

        Instant ins2 = Instant.ofEpochSecond(60);//较Unix元年加60秒
        System.out.println(ins2);//1970-01-01T00:01:00Z
    }

    //3.Duration:计算两个时间之间的间隔，Period：计算两个日期之间的间隔
    @Test
    public void test3() {
        Instant ins1 = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        Instant ins2 = Instant.now();
        Duration duration = Duration.between(ins1, ins2);
        System.out.println(duration.toMillis());//1000

        System.out.println("------------------------------");

        LocalTime lt1 = LocalTime.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        LocalTime lt2 = LocalTime.now();
        System.out.println(Duration.between(lt1, lt2).toMillis());//1001

        System.out.println("------------------------------");

        LocalDate ld1 = LocalDate.of(2015, 1, 1);
        LocalDate ld2 = LocalDate.now();
        Period period = Period.between(ld1, ld2);
        System.out.println(period.getYears());//2
        System.out.println(period.getMonths());//6
        System.out.println(period.getDays());//19
    }

    //TemporalAdjuster:时间校验器
    @Test
    public void test5() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);//2017-07-20T19:28:57.822

        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);//2017-07-10T19:28:57.822

        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));//调整为下个周日
        System.out.println(ldt3);//2017-07-23T19:31:39.479

        //自定义：下一个工作日
        LocalDateTime ldt5 = ldt.with((l) -> {
            LocalDateTime ldt4 = (LocalDateTime) l;
            DayOfWeek dow = ldt4.getDayOfWeek();//获取当前星期
            if (dow.equals(DayOfWeek.FRIDAY)) {//如果是周5，下个工作日即加3天
                return ldt4.plusDays(3);
            } else if (dow.equals(DayOfWeek.SATURDAY)) {//如果是周6，下个工作日即加2天
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);//其他，下个工作日即为明天
            }
        });
        System.out.println(ldt5);//2017-07-21T19:37:05.533
    }

    //DateTimeFormatter:格式化时间/日期
    @Test
    public void test6() {
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt = LocalDateTime.now();

        String strDate = ldt.format(dtf);
        System.out.println(strDate);

        System.out.println("------------------------");

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");//自定义格式化格式
        String strDate2 = dtf2.format(ldt);
        System.out.println(strDate2);//2017年07月20日 19:49:53

        LocalDateTime newDate = ldt.parse(strDate2, dtf2);//以指定格式解析字符串，重新获得LocalDateTime类型
        System.out.println(newDate);//2017-07-20T19:49:53
    }

    //ZonedDate、ZonedTime、ZonedDateTime
    @Test
    public void test7() {
        //获取支持的所有时区
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);

        //获取指定时区的日期时间类型
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Europe/Monaco"));
        System.out.println(ldt);//2017-07-20T14:01:23.417

        LocalDateTime ldt2 = LocalDateTime.now(ZoneId.of("Europe/Monaco"));
        //获取带时区的时间类型
        ZonedDateTime zdt = ldt2.atZone(ZoneId.of("Europe/Monaco"));
        System.out.println(zdt);//2017-07-20T14:01:23.420+02:00[Europe/Monaco]//与UTC时间有2个小时的时差
    }
}
