package com.desgin.mashibing.chainofresponsibility.abstractImpl.v3;

import java.util.List;

/**
 * 使用chain进行包装
 * @author lastwhisper
 */
public class Chain {
    private List<ChainHandler> handlers;
    private int index = 0;

    public Chain(List<ChainHandler> handlers) {
        this.handlers = handlers;
    }

    //将顺序关系封装
    public void proceed() {
        if (index >= handlers.size()) {
            return;
        }
        handlers.get(index++).execute(this);
    }
}
