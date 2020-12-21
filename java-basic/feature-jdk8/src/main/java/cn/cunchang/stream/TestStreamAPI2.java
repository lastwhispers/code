package cn.cunchang.stream;

import cn.cunchang.lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * 一、Stream API 的操作步骤：
 * 1. 创建 Stream
 * 2. 中间操作
 * 3. 终止操作(终端操作)
 */
public class TestStreamAPI2 {

    List<cn.cunchang.lambda.Employee> employees = Arrays.asList(
            new cn.cunchang.lambda.Employee("张三", 18, 9999.99),
            new cn.cunchang.lambda.Employee("李四", 58, 5555.55),
            new cn.cunchang.lambda.Employee("王五", 26, 3333.33),
            new cn.cunchang.lambda.Employee("赵六", 36, 6666.66),
            new cn.cunchang.lambda.Employee("田七", 12, 8888.88)
            //,
            //new Employee("田七", 12, 8888.88)
    );

    /**
     * 	 中间操作：筛选与切片
     * 		filter——接收 Lambda ， 从流中排除某些元素。
     * 		limit——截断流，使其元素不超过给定数量。
     * 		skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
     * 		distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     */
    //内部迭代：迭代操作由 Stream API 完成
    @Test
    public void test1() {
        //中间操作：不会执行任何操作
        Stream<cn.cunchang.lambda.Employee> stream = employees.stream()
                .filter((e) -> e.getAge() > 35);

        //终止操作：一次性执行全部内容，即 惰性求值
        stream.forEach(System.out::println);
    }

    //外部迭代
    @Test
    public void test2() {
        Iterator<cn.cunchang.lambda.Employee> it = employees.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    //发现“短路”只输出了两次，说明只要找到 2 个 符合条件的就不再继续迭代
    @Test
    public void test3() {
        employees.stream().filter(e -> {
            System.out.println("短路");
            return e.getSalary() > 5000;
        })
                .limit(2)
                .forEach(System.out::println);
    }

    @Test
    public void test4() {
        employees.parallelStream()
                .filter(e -> e.getSalary() >= 5000)//false过滤
                //.skip(2) //跳过两个
                .forEach(System.out::println);
    }

    @Test
    public void test5() {
        employees.stream()
                .distinct()//去重，注意：需要Employee重写hashCode 和 equals 方法
                .forEach(System.out::println);
    }

    /**
     * 中间操作：映射
     *  map--接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新元素。
     *  flatMap--接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void test6() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        list.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("------------------------");
        //map和flatMap的关系  类似于 add(Object)和addAll(Collection coll)
        Stream<Character> sm = list.stream().flatMap(TestStreamAPI2::filterCharacter);
        sm.forEach(System.out::println);

    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    /**
     * 中间操作：排序
     *  sorted()-自然排序（按照对象类实现Comparable接口的compareTo()方法 排序）
     *  sorted(Comparator com)-定制排序（Comparator）
     */
    @Test
    public void test7() {
        // string日期天然有序
        List<String> list = Arrays.asList("20191210", "20191211", "20191212");
        list.stream()
                .sorted()
                .forEach(System.out::println);


        employees.stream()
                .sorted((e1, e2) -> {
                    if (e1.getAge().equals(e2.getAge())) {
                        return e1.getName().compareTo(e2.getName());
                    } else {
                        return e1.getAge().compareTo(e2.getAge());
                    }
                }).forEach(System.out::println);

    }


    @Test
    public void test8() {
        List<Employee> list = employees.stream().filter(e -> e.getSalary() >= 5000).collect(Collectors.toList());
        System.out.println(list.size());
        System.out.println(employees.size());
    }
}
