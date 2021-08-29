package com.desgin.mashibing.chainofresponsibility.interfaceImpl.v5;


/**
 * @author lastwhisper
 */
public class Main {

    public static void main(String[] args) {
        // 初始化数据
        String msg = "大家好，QAQ， <script>alert('xss 注入')</script>，郭嘉，漂亮国！";
        Request request = new Request();
        request.setRequestStr(msg);
        Response response = new Response();
        response.setResponseStr(msg);
        // 过滤链1
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new HTMLFilter())
                .addFilter(new SesitiveFilter());
        // 过滤链2
        FilterChain filterChain2 = new FilterChain();
        filterChain2.addFilter(new FaceFilter());
        // 过滤链1+过滤链2
        filterChain.addFilter(filterChain2);

        // 处理前
        System.out.println(request.getRequestStr());
        System.out.println(response.getResponseStr());

        filterChain.doFilter(request, response, filterChain);

        // 处理后
        System.out.println(request.getRequestStr());
        System.out.println(response.getResponseStr());
    }
}
