package com.desgin.mashibing.chainofresponsibility.abstractImpl.v2;

/**
 * Created by cat on 2017-02-28.
 */
public abstract class ChainHandler {

    public void execute(Chain chain){
        handleProcess();
        chain.proceed();

    }

    protected abstract void handleProcess();
}
