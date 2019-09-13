package cn.lastwhisper.proxy.staticproxy;

/**
 * @author lastwhisper
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("RealSubject request to do");
    }

    @Override
    public void response() {
        System.out.println("RealSubject response to do");
    }
}
