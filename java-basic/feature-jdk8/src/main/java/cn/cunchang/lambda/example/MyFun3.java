package cn.cunchang.lambda.example;

@FunctionalInterface
public interface MyFun3<T, R> {

    public R operation(T t1, T t2);

}
