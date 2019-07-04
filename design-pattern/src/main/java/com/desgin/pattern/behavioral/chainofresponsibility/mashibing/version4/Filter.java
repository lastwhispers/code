package com.desgin.pattern.behavioral.chainofresponsibility.mashibing.version4;

public interface Filter {
    void doFilter(Request request,Response response,FilterChain chain);
}
