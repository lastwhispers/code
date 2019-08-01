package cn.lastwhisper.xmlannotation.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component //带注解的类，我们希望用这种方式定义bean，并让Spring把它放入容器中
public class Person {

    // Person依赖Car
    @Autowired
    //@Resource(name = )
    private Car car11;

    @Override
    public String toString() {
        return "Person{" +
                "car=" + car11 +
                '}';
    }
}