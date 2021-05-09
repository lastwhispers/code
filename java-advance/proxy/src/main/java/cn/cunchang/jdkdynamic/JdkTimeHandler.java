package cn.cunchang.jdkdynamic;

import cn.cunchang.Moveable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author cunchang
 * @date 2021/5/9 4:23 下午
 */
public class JdkTimeHandler implements InvocationHandler {

    private Moveable moveable;

    public JdkTimeHandler(Moveable moveable) {
        this.moveable = moveable;
    }

    /**
     * @param proxy  代理类
     * @param method 执行的方法
     * @param args   参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = method.invoke(moveable, new Object[]{});
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
        return result;
    }

}
