package cn.lastwhisper.feature5.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 创建代理类的实例，并完善InvocationHandler
 * @author lastwhisper
 */
public class Proxy2 {
    public static void main(String[] args) throws Exception {
        // 第一种方法、创建代理对象；使用有参构造
        Class<?> proxyClass = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
        Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
        Collection proxy1 = (Collection) constructor.newInstance(new InvocationHandler() {

            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
        proxy1.clear();
        // 为什么待返回值的方法会报错？
        // 因为InvocationHandler的invoke方法返回值为null
        proxy1.size();

        // 第二种方法、创建代理对象；使用Proxy提供的静态方法newProxyInstance
        // 参数一：目标增强类的类加载器（用于加载代理类）；
        // 参数二：目标增强类的接口（代理类实现接口）；
        // 参数三：InvocationHandler执行增强代码和目标方法
        Collection proxy2 = (Collection) Proxy.newProxyInstance(Collection.class.getClassLoader(),
                new Class[]{Collection.class},
                new InvocationHandler() {
                    ArrayList target = new ArrayList();

                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        long start = System.currentTimeMillis();
                        // 使用反射调用目标对象的方法
                        Object retVal = method.invoke(target, args);
                        long end = System.currentTimeMillis();
                        System.out.println("性能监控：" + method.getName() + "方法耗时 " + (end - start));
                        // 修改代理类的返回值（过滤器）
                        // retVal = false;
                        return retVal;
                    }
                });
        boolean retVal = proxy2.add("普通代理");
        proxy2.add("静态代理");
        proxy2.add("动态代理");
        proxy2.add("CGLIB代理");
        //System.out.println(retVal);
        System.out.println(proxy2.size());

    }
}
