package com.desgin.mashibing.chainofresponsibility.interfaceImpl.v4;

/**
 * @author lastwhisper
 */
public class HTMLFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        // 处理request请求
        request.requestStr = request.requestStr.replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;") + "---HTMLFilter.request()";
        System.out.println("HTMLFilter.request()");

        chain.doFilter(request, response, chain);

        // 处理response响应
        response.responseStr = response.responseStr.replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;") + "---HTMLFilter.response()";
        System.out.println("HTMLFilter.response()");
    }
}
