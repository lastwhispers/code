package cn.lastwhisper.v3.test;

import cn.lastwhisper.v3.mapper.AppMapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;

/**
 * 水平分表
 * @author lastwhisper
 */
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        AppMapper mapper = context.getBean(AppMapper.class);

        String currYear = "2016";
        //String sql = "insert into t_archievement_" + currYear + "(name) values('second')";
        String sql = "select * from t_archievement_" + currYear;
        System.out.println(mapper.executeQuery(sql));;

        context.close();
    }

}
