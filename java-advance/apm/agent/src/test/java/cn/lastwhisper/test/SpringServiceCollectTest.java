package cn.lastwhisper.test;

import cn.lastwhisper.monitor.agent.collects.SpringServiceCollects;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;
import javassist.NotFoundException;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author lastwhisper
 */
public class SpringServiceCollectTest {

    @Service
    public static class StringServiceMock {
        public void sayHello(String name) {
            System.out.println("hello" + name);
        }
    }

    @Test
    public void transformTest() throws Exception {
        System.setProperty("$bit_server","http://123.56.21.219:8860/receive");
        System.setProperty("$bit_key","c4f3508aee6058f3");
        System.setProperty("$bit_secret","966eedc1903454b8");
        SpringServiceCollects ins = SpringServiceCollects.INSTANCE;
        ClassLoader loader = SpringServiceCollectTest.class.getClassLoader();
        String className = "cn.lastwhisper.test.SpringServiceCollectTest$StringServiceMock";
        byte[] classFileBuffer = null;
        ClassPool pool = new ClassPool();
        pool.insertClassPath(new LoaderClassPath(loader));
        CtClass ctClass = pool.get(className);
        ins.transform(loader, className, classFileBuffer, ctClass);
        Class clazz = ctClass.toClass();
        StringServiceMock mock = new StringServiceMock();
        mock.sayHello("hanmeme");
        Thread.sleep(2000);
    }

    @Test
    public void isTargetTest() throws NotFoundException {
        String className = "cn.lastwhisper.test.SpringServiceCollectTest$StringServiceMock";
        ClassLoader loader = SpringServiceCollectTest.class.getClassLoader();
        ClassPool pool = new ClassPool(true);
        CtClass ctClass = pool.get(className);
        Assert.isTrue(SpringServiceCollects.INSTANCE.isTarget(className, loader, ctClass));
    }

    public static void main(String[] args){
        System.err.println("132123");
        System.out.println("132123");
    }
}
