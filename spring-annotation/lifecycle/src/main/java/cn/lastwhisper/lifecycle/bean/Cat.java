package cn.lastwhisper.lifecycle.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author lastwhisper
 */

@Component
public class Cat implements InitializingBean, DisposableBean {

    public Cat() {
        System.out.println("cat constructor...");
    }
    // 对象创建完成，并赋值之后调用
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("cat afterPropertiesSet...");
    }
    // 容器销毁bean时调用
    @Override
    public void destroy() throws Exception {
        System.out.println("cat destroy...");
    }


}
