package com.desgin.pattern.behavioral.chainofresponsibility.mashibing.version3;


/**
 * @author lastwhisper
 */
public class Main {

    public static void main(String[] args) {
        String msg = "大家好，QAQ， <script>alert('xss')</script>，敏感字眼，不学习！";
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new HTMLFilter())
                .addFilter(new SesitiveFilter());

        FilterChain filterChain2 = new FilterChain();
        filterChain2.addFilter(new FaceFilter());

        filterChain.addFilter(filterChain2);

        msg = filterChain.doFilter(msg);
        System.out.println(msg);
    }
}
