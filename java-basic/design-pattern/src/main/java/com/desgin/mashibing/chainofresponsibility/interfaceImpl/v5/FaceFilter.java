package com.desgin.mashibing.chainofresponsibility.interfaceImpl.v5;

/**
 * @author lastwhisper
 */
public class FaceFilter extends AbstractFilter {

    @Override
    protected void handlePreProcess(Request request, Response response) {
        // 处理request请求
        request.requestStr = request.requestStr.replaceAll("QAQ", "^V^")
                + "---FaceFilter.request()";
        System.out.println("FaceFilter.request()");
    }

    @Override
    protected void handlePostProcess(Request request, Response response) {
        // 处理response响应
        response.responseStr = response.responseStr.replaceAll("QAQ", "^V^")
                + "---FaceFilter.response()";
        System.out.println("FaceFilter.response()");
    }
}
