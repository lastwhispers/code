package cn.lastwhisper.v1.test;

import cn.lastwhisper.v1.db.DbContextHolder;
import cn.lastwhisper.v1.mapper.AppMapper;
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
