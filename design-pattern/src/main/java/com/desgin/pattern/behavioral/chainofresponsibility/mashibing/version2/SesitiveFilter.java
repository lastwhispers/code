package com.desgin.pattern.behavioral.chainofresponsibility.mashibing.version2;

/**
 * @author lastwhisper
 */
public class SesitiveFilter implements Filter {
    // process the sensitive words
    @Override
    public String doFilter(String str) {
        String r = str.replaceAll("不学习", "好好学习")
                .replaceAll("敏感字眼", "");
        return r;
    }
}
