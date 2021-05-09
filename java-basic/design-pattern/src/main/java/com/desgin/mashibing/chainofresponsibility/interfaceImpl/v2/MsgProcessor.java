package com.desgin.mashibing.chainofresponsibility.interfaceImpl.v2;

/**
 * @author lastwhisper
 */
public class MsgProcessor {
    String msg;

    Filter[] filters = {new HTMLFilter(), new SesitiveFilter(), new FaceFilter()};

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String process() {
        String r = msg;
        for (Filter filter : filters) {
            r = filter.doFilter(r);
        }
        return r;
    }
}
