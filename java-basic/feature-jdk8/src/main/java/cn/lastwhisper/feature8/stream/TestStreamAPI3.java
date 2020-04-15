package cn.lastwhisper.feature8.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/*
 * 一、Stream API 的操作步骤：
 * 1. 创建 Stream
 * 2. 中间操作
 * 3. 终止操作(终端操作)
 */
public class TestStreamAPI3 {

    List<Employee> employees = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Status.BUSY)
    );

    /**
     * 终止操作：查找与匹配
     */
    @Test
    public void test1() {
        // allMatch-检查是否匹配所有元素
        boolean b1 = employees.stream()
                .allMatch(e -> e.getStatus().equals(Status.BUSY));
        System.out.println(b1);//false

        // anyMatch-检查是否至少匹配一个元素
        boolean b2 = employees.stream()
                .anyMatch(e -> e.getStatus().equals(Status.BUSY));
        System.out.println(b2);//true

        // noneMatch-检查是否没有匹配所有元素
        boolean b3 = employees.stream()
                .noneMatch(e -> e.getStatus().equals(Status.BUSY));
        System.out.println(b3);//true

        // findFirst-返回第一个元素//Optional是Java8中避免空指针异常的容器类
        Optional<Employee> op = employees.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(op.get());

        // findAny-返回当前流中的任意元素
        Optional<Employee> op2 = employees.parallelStream()
                .filter(e -> e.getStatus().equals(Status.FREE))
                .findAny();
        System.out.println(op2.get());

        // count-返回流中元素的总个数
        Long count = employees.stream()
                .count();
        System.out.println(count);

        // max-返回流中最大值
        Optional<Employee> op3 = employees.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(op3.get());

        // min-返回流中最小值
        Optional<Double> op4 = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(op4.get());

    }

    /**
     * 终止操作：归约
     *   map和reduce的连接通常称为map-reduce 模式
     */
    @Test
    public void test3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        System.out.println("--------------------------");

        Optional<Double> op = employees.stream()//reduce(BinaryOperator b)//没有起始值，map返回可能为空，所以返回Optional类型
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(op.get());
    }

    /**
     * 终止操作：收集
     * collect-将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中元素做汇总的方法。
     */
    @Test
    public void test4() {
        List<String> list = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("----------------------------");

        Set<String> set = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("----------------------------");

        HashSet<String> hs = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hs.forEach(System.out::println);
    }

    @Test
    public void test5() {
        // 求和
        //long count1 = employees.stream().count();
        System.out.println("--------------求和--------------");
        Long count2 = employees.stream().collect(Collectors.counting());
        System.out.println(count2);


        //平均值
        System.out.println("--------------平均值--------------");
        Double avg = employees.stream().
                collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);

        //总和
        System.out.println("--------------总和--------------");
        Double sum = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);

        //最大值
        System.out.println("--------------最大值--------------");
        Optional<Employee> max = employees.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(max.get());

        //最小值
        System.out.println("--------------最小值--------------");
        Optional<Double> min = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(min.get());
    }

    // 分组
    @Test
    public void test6() {

        // 根据状态分组
        Map<Status, List<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        for (Map.Entry<Status, List<Employee>> entry : map.entrySet()) {
            System.out.println("----------------根据状态分组-----------------");
            System.out.println(entry.getKey());
            entry.getValue().forEach(System.out::println);
        }
    }

    // 根据状态进行分组求和
    @Test
    public void test11() {

        // 根据状态分组
        Map<Status, DoubleSummaryStatistics> statusSumSalaryMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus,
                        Collectors.summarizingDouble(Employee::getSalary)));
        for (Map.Entry<Status, DoubleSummaryStatistics> entry : statusSumSalaryMap.entrySet()) {
            System.out.println("----------------根据状态分组-----------------");
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().getSum());
        }
    }

    // 多级分组
    @Test
    public void test7() {
        // 多级分组，先根据状态进行分组，再根据年龄分组
        Map<Status, Map<String, List<Employee>>> map2 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus,
                        Collectors.groupingBy(e -> {
                            if (e.getAge() <= 35) {
                                return "青年";
                            } else if (e.getAge() <= 50) {
                                return "中年";
                            } else {
                                return "老年";
                            }
                        })));
        for (Map.Entry<Status, Map<String, List<Employee>>> entry : map2.entrySet()) {
            System.out.println("----------------多级分组：根据状态分组-----------------");
            System.out.println(entry.getKey());
            Map<String, List<Employee>> innerMap = entry.getValue();
            for (Map.Entry<String, List<Employee>> innerEntry : innerMap.entrySet()) {
                System.out.println("------------------------多级分组：根据年龄分组---------");
                System.out.println(innerEntry.getKey());
                innerEntry.getValue().forEach(System.out::println);
            }
        }
    }

    // 分区
    @Test
    public void test8() {
        //分区
        Map<Boolean, List<Employee>> map3 = employees.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 8000));
        for (Map.Entry<Boolean, List<Employee>> entry : map3.entrySet()) {
            System.out.println("----------------根据状态分区-----------------");
            System.out.println(entry.getKey());
            entry.getValue().forEach(System.out::println);
        }
    }

    //
    @Test
    public void test9() {
        String str = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining("," , "----", "----"));

        System.out.println(str);
    }

    @Test
    public void test10(){
        Optional<Double> sum = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.reducing(Double::sum));
        System.out.println(sum.get());
    }

}
