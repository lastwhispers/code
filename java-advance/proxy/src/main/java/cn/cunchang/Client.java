package cn.cunchang;

import cn.cunchang.cglib.RunMethodIntercepot;
import cn.cunchang.myjdkdynamic.*;
import cn.cunchang.jdkdynamic.JdkTimeHandler;
import cn.cunchang.staticproxy.combination.TankLogProxy1;
import cn.cunchang.staticproxy.combination.TankTimeProxy1;
import cn.cunchang.staticproxy.inherit.TankLogTimeProxy;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author cunchang
 * @date 2021/5/9 10:11 上午
 */
public class Client {

    @Test
    public void test继承代理() {
        Moveable tank = new TankLogTimeProxy();
        tank.move();
    }

    @Test
    public void test组合代理() {
        Tank tank = new Tank();
        // 先时间后日志
//        TankTimeProxy1 ttp = new TankTimeProxy1(tank);
//        TankLogProxy1 tlp = new TankLogProxy1(ttp);
//        tlp.move();
        // 先日志后时间
        TankLogProxy1 tlp = new TankLogProxy1(tank);
        TankTimeProxy1 ttp = new TankTimeProxy1(tlp);
        ttp.move();
    }

    /**
     * 动态代理原理
     * 1、动态生成代理类java源码
     * 2、通过java编译器将源码编译成字节码
     * 3、通过类加载器加载字节码，获取Class对象
     * 4、有了Class对象就可以通过反射获取代理对象
     *
     * @throws Exception
     */
    @Test
    public void test手写动态代理1() throws Exception {
        String path = System.getProperty("user.dir") + "/src/main/java/cn/cunchang/dynamic/";
        String pkg = "cn.cunchang.dynamic";
        MyClassLoader classLoader = new MyClassLoader(path, pkg);

        Moveable proxyObj = (Moveable) Proxy1.newProxyInstance(classLoader);
        proxyObj.move();
    }

    /**
     * 需要代理的方法，通过Class获取
     * @throws Exception
     */
    @Test
    public void test手写动态代理2() throws Exception {
        String path = System.getProperty("user.dir") + "/src/main/java/cn/cunchang/dynamic/";
        String pkg = "cn.cunchang.dynamic";
        MyClassLoader classLoader = new MyClassLoader(path, pkg);

        Moveable proxyObj = (Moveable) Proxy2.newProxyInstance(classLoader, Moveable.class);
        proxyObj.move();
    }

    /**
     * 代理的逻辑，通过InvocationHandler封装
     * @throws Exception
     */
    @Test
    public void test手写动态代理3() throws Exception {
        String path = System.getProperty("user.dir") + "/src/main/java/cn/cunchang/dynamic/";
        String pkg = "cn.cunchang.dynamic";
        MyClassLoader classLoader = new MyClassLoader(path, pkg);

        Tank tank = new Tank();
        InvocationHandler timeHandler = new TimeHandler(tank);
        Moveable proxyObj = (Moveable) Proxy3.newProxyInstance(classLoader, Moveable.class, timeHandler);
        proxyObj.move();
    }

    /**
     * newProxyInstance
     * |-- getProxyClass0
     * |--proxyClassCache.get(loader, interfaces);
     * |--ProxyClassFactory
     * |--apply
     * |--代理类的名称 String proxyName = proxyPkg + proxyClassNamePrefix + num;
     * |--生成字节码 byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
     * proxyName, interfaces, accessFlags);通过类加载器defineClass0加载
     * |--生成符合Java规范的代码 generateProxyClass
     * 在动态生成的对象中，使用h.invoke(this, m3, (Object[])null)这种形式执行方法
     * 这个h就是Proxy的InvocationHandler成员变量，而这个成员变量是在
     * Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this)传入this时赋值的
     */
    public void testJdk动态代理1() throws Exception {
        // 查看jdk生成的代理类，必须在main方法中才能生成
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        ClassLoader classLoader = Client.class.getClassLoader();
        Tank tank = new Tank();
        // 第二种方法、创建代理对象；使用Proxy提供的静态方法newProxyInstance
        // 参数一：目标增强类的类加载器（用于加载代理类）；
        // 参数二：目标增强类的接口（代理类实现接口）；
        // 参数三：InvocationHandler执行增强代码和目标对象
        Moveable proxyObj = (Moveable) Proxy.newProxyInstance(classLoader, Tank.class.getInterfaces(), new JdkTimeHandler(tank));
        proxyObj.move();

    }

    public static void main(String[] args) throws Exception {
//        new Client().testJdk动态代理1();
        new Client().testCglib动态代理1();
    }

    public void testCglib动态代理1() throws Exception {
        // 查看Cglib生成的代理类
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, System.getProperty("user.dir"));
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Tank.class);
        enhancer.setCallback(new RunMethodIntercepot());
        Moveable tank = (Moveable) enhancer.create();
        tank.move();
    }

    /**
     * 代理jdk集合
     * @throws Exception
     */
    @Test
    public void testJdk动态代理2() throws Exception {
        // 第一种方法、创建代理对象；使用有参构造
        Class<?> proxyClass = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
        Constructor<?> constructor = proxyClass.getConstructor(java.lang.reflect.InvocationHandler.class);
        Collection proxy1 = (Collection) constructor.newInstance(new java.lang.reflect.InvocationHandler() {
//            List target = new ArrayList();
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object retVal = null;
//                retVal = method.invoke(target, args);
                return retVal;
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
                new java.lang.reflect.InvocationHandler() {
                    ArrayList target = new ArrayList();

                    @Override
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
        System.out.println(retVal);
        System.out.println(proxy2.size());
    }


}
