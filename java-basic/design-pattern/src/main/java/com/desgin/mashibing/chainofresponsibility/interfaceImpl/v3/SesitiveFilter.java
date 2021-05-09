package com.desgin.mashibing.chainofresponsibility.interfaceImpl.v3;

/**
 * @author lastwhisper
 */
public class SesitiveFilter implements Filter {
    // process the sensitive words
    @Override
    public String doFilter(String str) {
        String r = str.replaceAll("郭嘉", "**")
                .replaceAll("漂亮国", "***");
        return r;
    }
}
