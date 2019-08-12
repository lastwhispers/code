package com.desgin.pattern.behavioral.chainofresponsibility.mashibing.version4;


/**
 * @author lastwhisper
 */
public class Main {

    public static void main(String[] args) {
        // 初始化数据
        String msg = "大家好，QAQ， <script>alert('xss')</script>，敏感字眼，不学习！";
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
        // 处理
        filterChain.doFilter(request, response, filterChain);

        System.out.println(request.getRequestStr());
        System.out.println(response.getResponseStr());
    }
}
