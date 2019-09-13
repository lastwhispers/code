package cn.lastwhisper.proxy.dynamicproxy;

import cn.lastwhisper.proxy.staticproxy.RealSubject;
import cn.lastwhisper.proxy.staticproxy.Subject;

/**
 * Created by cat on 2017-02-27.
 */
public class Client {

    public static void main(String[] args) {
        // 查看生成的代理类
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Subject proxy = (Subject) new JdkProxySubject(new RealSubject()).getProxy();
        //proxy.request();
        proxy.response();
    }
}
