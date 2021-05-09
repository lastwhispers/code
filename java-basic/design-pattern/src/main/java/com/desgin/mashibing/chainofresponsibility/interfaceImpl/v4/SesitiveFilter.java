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

        // 处理response响应
        response.responseStr = response.responseStr.replaceAll("郭嘉", "**")
                .replaceAll("漂亮国", "***") + "---SesitiveFilter.response()";
    }
}
