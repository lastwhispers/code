package cn.lastwhisper.jdk8.lambda.strategy;

/**
 * 过滤接口
 */
public interface Filter<T> {
    boolean eqCondition(T t);
}
