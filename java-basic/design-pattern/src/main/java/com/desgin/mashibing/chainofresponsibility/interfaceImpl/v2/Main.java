package com.desgin.mashibing.chainofresponsibility.interfaceImpl.v2;

/**
 * @author lastwhisper
 */
public class Main {

    public static void main(String[] args) {
        String msg = "大家好，QAQ， <script>alert('xss 注入')</script>，郭嘉，漂亮国！";
        MsgProcessor mp = new MsgProcessor();
        mp.setMsg(msg);
        msg = mp.process();
        System.out.println(msg);
    }
}
