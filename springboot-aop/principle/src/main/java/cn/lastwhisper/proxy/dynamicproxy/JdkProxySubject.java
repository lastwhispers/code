package cn.lastwhisper.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * aspect
 * @author lastwhisper
 */
public class JdkProxySubject implements InvocationHandler {
    // 目标类
    private Object target;

    public JdkProxySubject(Object target) {
        this.target = target;
    }

    /**
     * newProxyInstance
     *  |-- getProxyClass0
     *      |--proxyClassCache.get(loader, interfaces);
     *      |--ProxyClassFactory
     *          |--apply
     *          |--代理类的名称 String proxyName = proxyPkg + proxyClassNamePrefix + num;
     *          |--生成字节码 byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
     *                 proxyName, interfaces, accessFlags);通过类加载器defineClass0加载
     *      |--生成符合Java规范的代码 generateProxyClass
     *  在动态生成的对象中，使用h.invoke(this, m3, (Object[])null)这种形式执行方法
     *  这个h就是Proxy的InvocationHandler成员变量，而这个成员变量是在
     *  Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this)传入this时赋值的
     */
    public Object getProxy() {
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    /**
     * target：目标类
     * proxy：代理类
     * method：目标方法
     * args：目标方法参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method");
        Object result = null;
        try {
            result = method.invoke(target, args);
        } catch (Exception e) {
            System.out.println("ex:" + e.getMessage());
            throw e;//代理类不要生吞异常
        } finally {
            System.out.println("after method");
        }
        return result;
    }

}
