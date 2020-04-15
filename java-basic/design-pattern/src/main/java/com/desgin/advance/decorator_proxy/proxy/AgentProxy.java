package com.desgin.advance.decorator_proxy.proxy;

import com.desgin.advance.decorator_proxy.Boxer;

/**
 * 经纪人
 * @author lastwhisper
 * @date 2020/4/12
 */
public class AgentProxy implements Boxer {
    private Boxer boxer;

    public AgentProxy(Boxer boxer) {
        this.boxer = boxer;
    }

    @Override
    public void fight() {
        findOpponent();
        findSponsor();
        negotiate();
        System.out.println("让拳手去比赛");
        boxer.fight();
    }

    private void findOpponent() {
        System.out.println("经纪人：寻找适用于当前拳手的对手");
    }

    private void findSponsor() {
        System.out.println("经纪人：寻找赞助商");
    }

    private void negotiate() {
        System.out.println("经纪人：洽谈");
    }
}
