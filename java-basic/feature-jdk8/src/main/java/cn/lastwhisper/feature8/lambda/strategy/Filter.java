package cn.lastwhisper.feature8.lambda.strategy;

/**
 * 过滤接口
 */
public interface Filter<T> {
    boolean eqCondition(T t);
}
