package com.desgin.pattern.behavioral.chainofresponsibility.mashibing.version2;

/**
 * @author lastwhisper
 */
public class HTMLFilter implements Filter {
    // process the html tag <>
    @Override
    public String doFilter(String str) {
        String r = str.replaceAll("<", "[")
                .replaceAll(">", "]");
        return r;
    }
}
