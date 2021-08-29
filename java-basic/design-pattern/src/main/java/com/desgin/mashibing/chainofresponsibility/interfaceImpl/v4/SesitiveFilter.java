package com.desgin.mashibing.chainofresponsibility.interfaceImpl.v4;

/**
 * @author lastwhisper
 */
public class SesitiveFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        // 处理request请求
        request.requestStr = request.requestStr.replaceAll("郭嘉", "**")
                .replaceAll("漂亮国", "***") + "---SesitiveFilter.request()";
        System.out.println("SesitiveFilter.request()");

        chain.doFilter(request, response, chain);

        // 处理response响应
        response.responseStr = response.responseStr.replaceAll("郭嘉", "**")
                .replaceAll("漂亮国", "***") + "---SesitiveFilter.response()";
        System.out.println("SesitiveFilter.response()");
    }
}
