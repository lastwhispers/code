package com.desgin.pattern.behavioral.chainofresponsibility.mashibing.version4;

/**
 * @author lastwhisper
 */
public class HTMLFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        // 处理request请求
        request.requestStr = request.requestStr.replaceAll("<", "[")
                .replaceAll(">", "]") + "---HTMLFilter.request()";

        chain.doFilter(request, response, chain);

        // 处理response响应
        response.responseStr = response.responseStr.replaceAll("<", "[")
                .replaceAll(">", "]") + "---HTMLFilter.response()";
    }
}
