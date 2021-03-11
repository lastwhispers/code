package cn.cunchang.collection;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 集合运算 - Java实现集合的交、并、差
 * @author cunchang
 * @date 2020/7/22 11:43
 */
public class Intersection {

    @Test
    public void test1(){

    }

    public static void main(String[] args) {
        Set<Integer> result = new HashSet<Integer>();
        Set<Integer> set1 = new HashSet<Integer>() {
            private static final long serialVersionUID = 1L;
            {
                add(1);
                add(3);
                add(5);
            }};

        Set<Integer> set2 = new HashSet<Integer>(){
            private static final long serialVersionUID = 1L;
            {
                add(1);
                add(2);
                add(3);
            }};
        System.out.println("set1:"+set1);
        System.out.println("set2:"+set2);
        //交集

        result.addAll(set1);
        result.retainAll(set2);
        System.out.println("交集:"+result);

        //差集
        result.clear();
        result.addAll(set1);
        result.removeAll(set2);
        System.out.println("差集:"+result);

        //并集
        result.clear();
        result.addAll(set1);
        result.addAll(set2);
        System.out.println("并集:"+result);

    }

}
