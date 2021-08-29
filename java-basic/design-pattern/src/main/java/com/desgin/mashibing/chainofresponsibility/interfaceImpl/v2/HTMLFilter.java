package com.desgin.mashibing.chainofresponsibility.interfaceImpl.v2;

/**
 * @author lastwhisper
 */
public class HTMLFilter implements Filter {
    // process the html tag <>
    @Override
    public String doFilter(String str) {
        return str.replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;");
    }
}
