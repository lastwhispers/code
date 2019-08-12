package cn.lastwhisper.jdk5.feature.proxy.aop;

import java.lang.reflect.Method;

/**
 * 方法增强的接口
 */
public interface Advice {

    void afterMethod(Method method);

    void beforeMethod(Method method);

}
