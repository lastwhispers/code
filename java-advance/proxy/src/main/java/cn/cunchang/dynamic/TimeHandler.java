package cn.cunchang.dynamic;

import java.lang.reflect.Method;

public class TimeHandler implements InvocationHandler{

    //被代理类
    private Object target;
    

    public TimeHandler(Object target) {
        super();
        this.target = target;
    }

    @Override
    public void invoke(Object o,Method m) {
        long start = System.currentTimeMillis();
        try {
            m.invoke(target, new Object[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
        
    }
}