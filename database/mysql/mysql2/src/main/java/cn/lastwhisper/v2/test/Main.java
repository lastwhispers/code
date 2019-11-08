package cn.lastwhisper.v2.test;

import cn.lastwhisper.v2.db.DbContextHolder;
import cn.lastwhisper.v2.mapper.AppMapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lastwhisper
 */
public class Main {

    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        AppMapper mapper = context.getBean(AppMapper.class);
        //DbContextHolder.set(DbContextHolder.MASTER);
        System.out.println(mapper.executeQuery("select * from user"));
        //DbContextHolder.set(DbContextHolder.SLAVE);
        System.out.println(mapper.executeQuery2("select * from user"));
        context.close();
    }

}
