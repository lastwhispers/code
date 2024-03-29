package com.desgin.advance.decorator_vs_proxy;

import com.desgin.advance.decorator_vs_proxy.decorator.FastShoesDecorator;
import com.desgin.advance.decorator_vs_proxy.proxy.AgentProxy;
import org.junit.Test;

/**
 * @author lastwhisper
 * @date 2020/4/12
 */
public class Client {

    // 代理模式
    @Test
    public void test1() {
        Boxer boxer = new BoxerImpl();
        Boxer agent = new AgentProxy(boxer);
        System.out.println("准备比赛！");
        agent.fight();
        System.out.println("比赛结束！");
    }

    // 装饰者模式
    @Test
    public void test2() {
        Boxer boxer = new BoxerImpl();
        FastShoesDecorator fastShoesDecorator = new FastShoesDecorator(boxer);
        Boxer agent = new AgentProxy(fastShoesDecorator);
        System.out.println("准备比赛！");
        fastShoesDecorator.moveFast();
        agent.fight();
        System.out.println("比赛结束！");
    }

}
