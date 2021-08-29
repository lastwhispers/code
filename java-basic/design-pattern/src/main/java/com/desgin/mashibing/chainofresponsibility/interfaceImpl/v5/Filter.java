package com.desgin.mashibing.chainofresponsibility.interfaceImpl.v5;

public interface Filter {
    /**
     * 责任链
     * @param request
     * @param response
     * @param chain
     */
    void doFilter(Request request, Response response, FilterChain chain);
}
