package com.desgin.mashibing.chainofresponsibility.interfaceImpl.v4;

/**
 * @author lastwhisper
 */
public class FaceFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        // 处理request请求
        request.requestStr = request.requestStr.replaceAll("QAQ", "^V^")
                + "---FaceFilter.request()";
        System.out.println("FaceFilter.request()");

        chain.doFilter(request, response, chain);

        // 处理response响应
        response.responseStr = response.responseStr.replaceAll("QAQ", "^V^")
                + "---FaceFilter.response()";
        System.out.println("FaceFilter.response()");
    }
}
