package com.desgin.pattern.behavioral.chainofresponsibility.mashibing.version1;

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
        String r = msg.replaceAll("<", "[")
                .replaceAll(">", "]");
        // process the sensitive words
        r= r.replaceAll("不学习", "好好学习")
                .replaceAll("敏感字眼", "");

        return r;
    }
}
