package com.desgin.mashibing.chainofresponsibility.interfaceImpl.v5;

/**
 * @author lastwhisper
 */
public class HTMLFilter extends AbstractFilter {

    @Override
    protected void handlePreProcess(Request request, Response response) {
        // 处理request请求
        request.requestStr = request.requestStr.replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;") + "---HTMLFilter.request()";
        System.out.println("HTMLFilter.request()");
    }

    @Override
    protected void handlePostProcess(Request request, Response response) {
        // 处理response响应
        response.responseStr = response.responseStr.replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;") + "---HTMLFilter.response()";
        System.out.println("HTMLFilter.response()");
    }
}
