package com.desgin.mashibing.chainofresponsibility.abstractImpl.v3;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cat on 2017-02-28.
 */
public class ChainClient {
    static class ChainHandlerA extends ChainHandler {
        @Override
        protected void handlePreProcess() {
            System.out.println("ChainHandlerA handlePreProcess");
        }

        @Override
        protected void handlePostProcess() {
            System.out.println("ChainHandlerA handlePostProcess");
        }
    }

    static class ChainHandlerB extends ChainHandler {
        @Override
        protected void handlePreProcess() {
            System.out.println("ChainHandlerB handlePreProcess");
        }

        @Override
        protected void handlePostProcess() {
            System.out.println("ChainHandlerB handlePostProcess");
        }
    }

    static class ChainHandlerC extends ChainHandler {
        @Override
        protected void handlePreProcess() {
            System.out.println("ChainHandlerC handlePreProcess");
        }

        @Override
        protected void handlePostProcess() {
            System.out.println("ChainHandlerC handlePostProcess");
        }
    }

    public static void main(String[] args) {
        List<ChainHandler> handlers = Arrays.asList(
                new ChainHandlerA(),
                new ChainHandlerB(),
                new ChainHandlerC()
        );
        Chain chain = new Chain(handlers);
        chain.proceed();


    }
}
