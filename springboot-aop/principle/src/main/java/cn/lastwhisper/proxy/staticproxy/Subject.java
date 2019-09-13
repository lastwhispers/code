package cn.lastwhisper.proxy.staticproxy;

/**
 * @author lastwhisper
 */
public interface Subject {
    public void request();
    // 新增接口,静态代理需要重新编写代理的代码，而动态代理不用
    public void response();
}
