//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.cunchang.v1.equal;

import cn.cunchang.v2.Subject;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * 继承jdk的Proxy，作用之一：直接使用handler成员变量
 * 副作用：由于Java只能单继承，所以jdk动态代理只能代理有接口实现的类
 */
public final class $Proxy0 extends Proxy implements Subject {
    private static Method m1;
    private static Method m3;
    private static Method m2;
    private static Method m0;

    /**
     * InvocationHandler 是代理对象的调用被代理对象的接口。
     * 也就是说代理对象为什么能调用被代理对象，是因为有这个handler在中间做桥梁
     * @param var1
     */
    public $Proxy0(InvocationHandler var1)  {
        super(var1);
    }

    static {
        try {
            /**
             * 获取代理对象接口的所有方法，用于代理方法回调
             */
            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            m3 = Class.forName("cn.cunchang.v1.equal.Subject").getMethod("sayHello");
            m2 = Class.forName("java.lang.Object").getMethod("toString");
            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }

    public final String sayHello()  {
        try {
            /**
             * 代理类调用handler，handler.invoke()方法，把方法的执行链交给用户
             *
             * InvocationHandler.invoke(Object proxy,  Method method, Object[] args)
             * proxy – jdk给你生成的代理对象
             * method – 被代理类的方法
             * args – 被代理类方法的参数
             */
            return (String)super.h.invoke(this, m3, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final boolean equals(Object var1)  {
        try {
            return (Boolean)super.h.invoke(this, m1, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final String toString()  {
        try {
            return (String)super.h.invoke(this, m2, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final int hashCode()  {
        try {
            return (Integer)super.h.invoke(this, m0, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }


}
