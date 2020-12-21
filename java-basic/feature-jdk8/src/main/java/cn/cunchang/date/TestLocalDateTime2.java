package cn.cunchang.date;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期字符串转Date
 * @author lastwhisper
 * @date 2019/10/25
 */
public class TestLocalDateTime2 {

    @Test
    public void testStringToLocalDate() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy MM dd");
        String localTime = "2019 10 25";
        LocalDateTime ldt = LocalDateTime.parse(localTime, df);
        System.out.println("String类型的时间转成LocalDateTime：" + ldt);
        ////自定义格式化格式
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        //LocalDateTime ldt = LocalDateTime.parse("20191022", dtf);
        //System.out.println(ldt);
    }

    @Test
    public void testStringToDate() {
        String strDate = "20191022";
        //注意：SimpleDateFormat构造函数的样式与strDate的样式必须相符
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        //必须捕获异常
        try {
            Date date = simpleDateFormat.parse(strDate);
            System.out.println(date);
        } catch (ParseException px) {
            px.printStackTrace();
        }
    }

    // 日期之差
    @Test
    public void test2() {
        String strDate1 = "20191022";
        String strDate2 = "20191024";
        //注意：SimpleDateFormat构造函数的样式与strDate的样式必须相符
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        //必须捕获异常
        try {
            Date date1 = simpleDateFormat.parse(strDate1);
            Date date2 = simpleDateFormat.parse(strDate2);
            Instant instant1 = date1.toInstant();
            Instant instant2 = date2.toInstant();
            System.out.println(Duration.between(instant1, instant2).toDays());
        } catch (ParseException px) {
            px.printStackTrace();
        }
    }
}
