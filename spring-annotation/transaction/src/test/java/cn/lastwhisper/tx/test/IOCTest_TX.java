package cn.lastwhisper.tx.test;

import cn.lastwhisper.tx.TxConfig;
import cn.lastwhisper.tx.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lastwhisper
 */
public class IOCTest_TX {


    /**
     * 测试事务
     *
     * @param
     * @return void
     */
    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TxConfig.class);
        System.out.println("容器初始化...");

        UserService userService = applicationContext.getBean(UserService.class);
        userService.insertUser();

        applicationContext.close();
        //printBeans(applicationContext);
    }

    private void printBeans(ApplicationContext applicationContext) {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }

}
