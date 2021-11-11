package cn.cunchang;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author cunchang
 * @date 2021/7/13 9:55 上午
 */
public class TestSomething {

    @Test
    public void test1() {
        String payload = IntStream.rangeClosed(1, 1000000)
                .mapToObj((item) -> "a")
                .collect(Collectors.joining(""));
        System.out.println(payload.length());
    }

    @Test
    public void test2() {
//        LocalDate startDate = LocalDate.now().plusDays(-17L);
        LocalDate startDate = LocalDate.parse("20210701", DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate endDate = LocalDate.parse("20210713", DateTimeFormatter.ofPattern("yyyyMMdd"));

        endDate = endDate.plusDays(1L);

        Period between = Period.between(startDate, endDate);
        System.out.println(between.getDays());

        int i=0;
        while (startDate.isBefore(endDate)) {
            i++;
            System.out.println(startDate);
               startDate = startDate.plusDays(1L);
        }
        System.out.println("i:"+i);
    }

}
