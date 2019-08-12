package cn.lastwhisper.jdk5.feature.proxy.aopframework;

import java.io.InputStream;
import java.util.Collection;

public class AopFrameworkTest {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        InputStream ips = AopFrameworkTest.class.getClassLoader().getResourceAsStream("config.properties");
        Object bean = new BeanFactory(ips).getBean("xxx");
        System.out.println(bean.getClass().getName());
        Collection proxyClass = (Collection) bean;
        proxyClass.add("1111");
        proxyClass.add("2222");
        proxyClass.add("3333");
        System.out.println(proxyClass.size());
    }

}
