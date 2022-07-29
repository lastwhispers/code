package cn.cunchang.aop;

import cn.cunchang.domain.BenzCar;
import cn.cunchang.domain.Car;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * 汽车类的切面CarAdvisor类
 * 实现接口：StaticMethodMatcherPointcutAdvisor
 *                     实现StaticMethodMatcherPointcutAdvisor接口唯一需要定义的是matches()方法，
 * 
 */
public class CarAdvisor2 extends StaticMethodMatcherPointcutAdvisor{

    /**
     * 切点方法 匹配 
     * 匹配规则：方法名为driving
     *                默认情况下，匹配所有的类
     */
    @Override
    public boolean matches(Method method, Class<?> clazz) {
        return "driving".equals(method.getName());
    }
    
    /**
     * 通过覆盖getClassFilter()方法，让它仅匹配BenzCar类及其子类
     */
    @Override
    public ClassFilter getClassFilter(){
        return new ClassFilter() {
            /**
             * 切点类 匹配
             * 匹配规则：为BenzCar类或其子类
             */
            @Override
            public boolean matches(Class<?> clazz) {
                return Car.class.isAssignableFrom(clazz);
            }
        };
    }
    
}