package reflect;

import java.lang.reflect.Method;

/**
 * Created by shfq on 2016/10/17.
 */
public class Test {
    public static void main(String[] args) {
        Parent parent = new Parent();
        Child child = new Child();

        try {
            Method parentMethod = parent.getClass().getDeclaredMethod("foo");
            Method childMethod = child.getClass().getDeclaredMethod("foo");

            parentMethod.invoke(parent); // this is parent
            childMethod.invoke(child); // this is child

            parentMethod.invoke(child); // this is child 反射调用时 子类对象调用了父类的方法最终执行得还是子类的方法而不是父类的方法
            childMethod.invoke(parent); // 这一行代码会报错 相当于是父类调用了子类的方法，父类根本就没有这个方法反射调用当然也会报错

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
