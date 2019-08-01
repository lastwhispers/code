package cn.lastwhisper.profile.test;

import cn.lastwhisper.profile.bean.Yellow;
import cn.lastwhisper.profile.config.MainConfigofProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @author lastwhisper
 */
public class IOCTest_Profile {


    /**
     * 测试profile
     *  1）使用命令参数加载profile
     *      -Dspring.profiles.active=test
     *  2）使用AnnotationConfigApplicationContext无参构造器
     *
     * @param
     * @return void
     */
    @Test
    public void test01() {

        //AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigofProfile.class);

        // 1.使用无参构造器创建对象
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 2.设置需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("dev");
        // 3.加载主配置类
        applicationContext.register(MainConfigofProfile.class);
        // 4.刷新容器
        applicationContext.refresh();

        String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for (String name : beanNamesForType) {
            System.out.println(name);
        }

        Yellow yellow = applicationContext.getBean(Yellow.class);
        System.out.println(yellow);
    }

}
