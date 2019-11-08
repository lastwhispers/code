package cn.lastwhisper.jdk8.stream;

import cn.lastwhisper.jdk8.lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
 * 一、Stream API 的操作步骤：
 * 1. 创建 Stream
 * 2. 中间操作
 * 3. 终止操作(终端操作)
 */
public class TestStreamAPI {

    /**
     * 创建Stream
     */
    @Test
    public void test1() {
        // 1.可以通过Collection 系列集合提供的stream()或parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        // 2.通过 Arrays 中的静态方法stream()获取数组流
        Employee[] emps = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(emps);

        // 3.通过Stream 类中的静态方法of()
        Stream<String> stream3 = Stream.of("a", "b", "c");

        // 4.创建无限流
        // 迭代
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream4.limit(10).forEach(System.out::println);
        // 生成
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }

    @Test
    public void test2(){
        Stream.of(1,2,3,4,5,6,7,8,9)
                .parallel();
        Stream.of(1,2,3,4,5,6,7,8,9)
                .sequential();
    }

}
