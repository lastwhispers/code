package cn.lastwhisper.proxy.cglibproxy;

import cn.lastwhisper.proxy.staticproxy.RealSubject;
import cn.lastwhisper.proxy.staticproxy.Subject;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author lastwhisper
 */
public class Client {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealSubject.class);
        enhancer.setCallback(new DemoMethodInterceptor());
        Subject subject = (Subject) enhancer.create();
        subject.request();
    }
}
