package com.desgin.pattern.behavioral.chainofresponsibility.mashibing.version4;

/**
 * @author lastwhisper
 */
public class FaceFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        // 处理request请求
        request.requestStr = request.requestStr.replaceAll("QAQ", "^V^")
                + "---FaceFilter.request()";

        // 处理response响应
        response.responseStr = response.responseStr.replaceAll("QAQ", "^V^")
                + "---FaceFilter.response()";
    }
}
