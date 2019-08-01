package cn.lastwhisper.autowired.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lastwhisper
 */
@Component
public class Boss {
    private Car car;

    //@Autowired
    public Boss(Car car) {
        this.car = car;
        System.out.println("Boss有参构造器...");
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }
}
