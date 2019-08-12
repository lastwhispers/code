package com.desgin.pattern.behavioral.chainofresponsibility.mashibing.version4;

/**
 * @author lastwhisper
 */
public class SesitiveFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        // 处理request请求
        request.requestStr = request.requestStr.replaceAll("不学习", "好好学习")
                .replaceAll("敏感字眼", "") + "---SesitiveFilter.request()";
        // 处理response响应
        response.responseStr = response.responseStr.replaceAll("不学习", "好好学习")
                .replaceAll("敏感字眼", "") + "---SesitiveFilter.response()";
    }
}
