package com.desgin.pattern.creational.singleton;

/**
 * Create by lastwhisper on 2019/1/25
 */
public class LazyDoubleCheckSingleton {
    private static volatile LazyDoubleCheckSingleton lazyDoubleCheckSingleton = null;
    private LazyDoubleCheckSingleton(){

    }
    public static LazyDoubleCheckSingleton getInstance(){
        if(lazyDoubleCheckSingleton == null){
            synchronized (LazyDoubleCheckSingleton.class){
                if(lazyDoubleCheckSingleton == null){
                    lazyDoubleCheckSingleton = new LazyDoubleCheckSingleton();
                    //第一步 分配内存给这个对象
                    //第二步 初始化这个对象
                    //第三步 设置lazyDoubleCheckSingleton 指向刚分配的内存地址
                }
            }
        }
        return lazyDoubleCheckSingleton;
    }
}
