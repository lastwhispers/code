package com.desgin.pattern.behavioral.chainofresponsibility.mashibing.version1;

/**
 * @author lastwhisper
 */
public class Main {

    public static void main(String[] args) {
        String msg = "大家好，QAQ， <script>alert('xss')</script>，敏感字眼，不学习！";
        MsgProcessor mp = new MsgProcessor();
        mp.setMsg(msg);
        msg = mp.process();
        System.out.println(msg);
    }
}
