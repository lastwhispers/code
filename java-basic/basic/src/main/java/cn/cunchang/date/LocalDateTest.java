package cn.cunchang.date;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * @author cunchang
 * @date 2021/7/14 11:31 上午
 */
@Slf4j
public class LocalDateTest {

    /**
     * 日期循环
     */
    @Test
    public void test1() {
        LocalDate startDate = LocalDateUtil.parse4YYYYMMdd("20210601");
        LocalDate endDate = LocalDateUtil.parse4YYYYMMdd("20210610");

        // [20210601,20210610)
//        while (startDate.isBefore(endDate)) {
//            System.out.println(startDate);
//            startDate = startDate.plusDays(1L);
//        }

        // (20210601,20210610]
//        while (endDate.isAfter(startDate)) {
//            System.out.println(endDate);
//            endDate = endDate.plusDays(-1L);
//        }

        // [20210601,20210610]
        while (!startDate.isAfter(endDate)) {
            System.out.println(startDate);
            startDate = startDate.plusDays(1L);
        }

        // [20210601,20210610]
//        while (!endDate.isBefore(startDate)) {
//            System.out.println(endDate);
//            endDate = endDate.plusDays(-1L);
//        }
    }

    /**
     * 日期格式化
     */
    @Test
    public void test2() {
        log.info(AggregateTypeEnum.月.format(LocalDate.now()));
    }

    /**
     * 日期分割
     */
    @Test
    public void test3() {
        LocalDate startDate = LocalDateUtil.parseStr("20210101", LocalDateUtil.yyyyMMdd);
        LocalDate endDate = LocalDateUtil.parseStr("20210630", LocalDateUtil.yyyyMMdd);

        AggregateTypeEnum aggregateTypeEnum = AggregateTypeEnum.月;

        // 分割日期
        List<Pair<LocalDate, LocalDate>> regionDates = LocalDateUtil.splitByStep(startDate, endDate, aggregateTypeEnum.getStep());

        log.info(JSONObject.toJSONString(regionDates));
    }

    @Test
    public void test4() {
        LocalDate startDate=LocalDate.now().plusDays(-1),  endDate=LocalDate.now();
        // 日期之差
        Long dayDiv = endDate.toEpochDay() - startDate.toEpochDay();
        System.out.println("startDate:"+startDate+" endDate:"+endDate +" dayDiv:"+dayDiv);
    }

}
