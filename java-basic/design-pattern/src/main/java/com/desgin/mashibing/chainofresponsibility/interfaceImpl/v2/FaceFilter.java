package com.desgin.mashibing.chainofresponsibility.interfaceImpl.v2;

/**
 * @author lastwhisper
 */
public class FaceFilter implements Filter {
    // process the face tag <>
    @Override
    public String doFilter(String str) {
        return str.replaceAll("QAQ", "^V^");
    }

}
