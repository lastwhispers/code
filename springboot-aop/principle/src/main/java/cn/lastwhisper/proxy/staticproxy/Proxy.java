package cn.lastwhisper.proxy.staticproxy;

/**
 * @author lastwhisper
 */
public class Proxy {
    private Subject target;

    public Proxy(Subject target) {
        this.target = target;
    }

    public void request() {
        System.out.println("before method");
        target.request();
        System.out.println("after method");
    }

    public void response() {
        System.out.println("before method");
        target.response();
        System.out.println("after method");
    }
}
