package cn.lastwhisper.proxy.staticproxy;

/**
 * Created by cat on 2017-02-27.
 */
public class Client {

    public static void main(String[] args){
        Proxy subject = new Proxy(new RealSubject());
        subject.request();
    }

}
