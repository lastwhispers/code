package cn.lastwhisper.lifecycle.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author lastwhisper
 */
@Component
public class Dog implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public Dog() {
        System.out.println("dog constructor...");
    }

    // 对象创建完成，并赋值之后调用
    @PostConstruct
    public void init() throws Exception {
        System.out.println("dog @PostConstruct...");
    }

    // 容器销毁bean时调用
    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("dog @PreDestroy...");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
