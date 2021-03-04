package cn.cunchang.date;

import java.time.LocalDate;

/**
 * @author cunchang
 * @date 2021/1/10 6:18 下午
 */
public class LocalDateTest {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.ofEpochDay(1610273688163L);
        System.out.println(localDate);
    }

}
