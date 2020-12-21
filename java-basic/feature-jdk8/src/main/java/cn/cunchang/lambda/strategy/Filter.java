package cn.cunchang.lambda.strategy;

/**
 * 过滤接口
 */
public interface Filter<T> {
    boolean eqCondition(T t);
}
