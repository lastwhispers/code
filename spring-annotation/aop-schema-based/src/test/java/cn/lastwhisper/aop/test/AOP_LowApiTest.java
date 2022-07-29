package cn.lastwhisper.aop.test;

import cn.cunchang.domain.BmwCar;
import cn.cunchang.domain.BenzCar;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lastwhisper
 */
public class AOP_LowApiTest {

    @Test
    public void test01() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop-low-api.xml");

        BenzCar benz = (BenzCar) applicationContext.getBean("benz");
        BmwCar bmw = (BmwCar) applicationContext.getBean("bmw");

        benz.driving();   //奔驰车的driving方法
        System.out.println("===========================");
        bmw.driving();   //宝马车的driving方法

    }

}
