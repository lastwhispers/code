package cn.lastwhisper.test;

import cn.lastwhisper.db.DbContextHolder;
import cn.lastwhisper.mapper.AppMapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lastwhisper
 */
public class Main {

    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        AppMapper mapper = context.getBean(AppMapper.class);
        //DbContextHolder.set(DbContextHolder.MASTER);
        DbContextHolder.set(DbContextHolder.SLAVE);
        System.out.println(mapper.executeQuery("select * from user"));
        context.close();
    }

}
