package com.desgin.mashibing.chainofresponsibility.abstractImpl.v3;

/**
 * Created by cat on 2017-02-28.
 */
public abstract class ChainHandler {

    // 模板方法
    public void execute(Chain chain){
        handlePreProcess();
        chain.proceed();
        handlePostProcess();
    }

    protected abstract void handlePreProcess();
    protected abstract void handlePostProcess();
}
