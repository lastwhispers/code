package com.desgin.mashibing.chainofresponsibility.interfaceImpl.v5;


/**
 * @author cunchang
 * @date 2021/5/11 12:58 下午
 */
public abstract class AbstractFilter implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        handlePreProcess(request,response);
        chain.doFilter(request,response,chain);
        handlePostProcess(request,response);
    }

    /**
     * 责任链前置处理
     * @param request
     * @param response
     */
    protected abstract void handlePreProcess(Request request, Response response);

    /**
     * 责任链后置处理
     * @param request
     * @param response
     */
    protected abstract void handlePostProcess(Request request, Response response);
}
