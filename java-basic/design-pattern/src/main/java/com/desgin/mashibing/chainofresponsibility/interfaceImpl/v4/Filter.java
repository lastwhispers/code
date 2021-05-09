package com.desgin.mashibing.chainofresponsibility.interfaceImpl.v4;

public interface Filter {
    void doFilter(Request request, Response response, FilterChain chain);
}
