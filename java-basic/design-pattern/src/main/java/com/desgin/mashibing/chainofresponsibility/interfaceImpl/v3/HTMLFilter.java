package com.desgin.mashibing.chainofresponsibility.interfaceImpl.v3;

/**
 * @author lastwhisper
 */
public class HTMLFilter implements Filter {
    // process the html tag <>
    @Override
    public String doFilter(String str) {
        String r = str.replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;");
        return r;
    }
}
