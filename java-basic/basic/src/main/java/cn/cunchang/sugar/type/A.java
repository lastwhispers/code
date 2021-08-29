package cn.cunchang.sugar.type;

// A.jar
//A.java有类A，A调了B的方法add(int i)，这时传的是个原始类型， 完美匹配
public class A {
    public static void main(String[] args) {
        // add(int i) ，1不会进行拆装箱
        // add(Integer i)，1会进行拆装箱
        B.add(1);
    }
}
