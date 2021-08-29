package cn.cunchang.date;

import org.junit.Test;

import java.time.LocalDate;

/**
 * @author cunchang
 * @date 2021/7/14 11:31 上午
 */
public class LocalDateTest {

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
//        while (!startDate.isAfter(endDate)) {
//            System.out.println(startDate);
//            startDate = startDate.plusDays(1L);
//        }

        // [20210601,20210610]
        while (!endDate.isBefore(startDate)) {
            System.out.println(endDate);
            endDate = endDate.plusDays(-1L);
        }

    }

}
