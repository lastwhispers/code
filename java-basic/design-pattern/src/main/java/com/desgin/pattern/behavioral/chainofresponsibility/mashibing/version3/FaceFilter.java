package com.desgin.pattern.behavioral.chainofresponsibility.mashibing.version3;

/**
 * @author lastwhisper
 */
public class FaceFilter implements Filter {
    // process the face tag <>
    @Override
    public String doFilter(String str) {
        String r = str.replaceAll("QAQ", "^V^");
        return r;
    }

}
