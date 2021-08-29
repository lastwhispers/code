package com.desgin.mashibing.chainofresponsibility.interfaceImpl.v1;

/**
 * @author lastwhisper
 */
public class MsgProcessor {
    String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String process() {
        // process the html tag <>
        String r = msg.replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;");
        // process the sensitive words
        r= r.replaceAll("郭嘉", "**")
                .replaceAll("漂亮国", "***");

        return r;
    }
}
