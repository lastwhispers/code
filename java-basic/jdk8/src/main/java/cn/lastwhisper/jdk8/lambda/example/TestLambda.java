package cn.lastwhisper.jdk8.lambda.example;

import cn.lastwhisper.jdk8.lambda.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author lastwhisper
 */
public class TestLambda {

    List<Employee> emps = Arrays.asList(
            new Employee(101, "a", 18, 9999.99),
            new Employee(102, "b", 59, 6666.66),
            new Employee(103, "c", 28, 3333.33),
            new Employee(104, "d", 8, 7777.77),
            new Employee(105, "e", 38, 5555.55)
    );

    /**
     * 调用Collections.sort()方法，通过定制排序，
     * 比较两个Employee(先按年龄比，年龄相同按姓名比),使用Lambda表达式作为参数传递。
     */
    @Test
    public void test1() {
        //Collections.sort(emps, new Comparator<Employee>() {
        //    @Override
        //    public int compare(Employee o1, Employee o2) {
        //        if (o1.getAge() == o2.getAge()) {
        //            return o1.getName().compareTo(o2.getName());
        //        }
        //        return o1.getAge() - o2.getAge();
        //    }
        //});

        Collections.sort(emps, (o1, o2) -> {
            if (o1.getAge() == o2.getAge()) {
                return o1.getName().compareTo(o2.getName());
            }
            return o1.getAge() - o2.getAge();
        });
        for (Employee emp : emps) {
            System.out.println(emp);
        }
    }

    /**
     * （1）声明函数式接口，接口中声明抽象方法：public String getValue(String str);
     * （2）声明类LambdaTest，类中编写方法使用接口作为参数，将一个字符串转换成大写，并作为方法的返回值。
     * （3）再将一个字符串的第2个到第4个索引位置进行截取子串。
     */
    @Test
    public void test2() {
        String str = "abcde";
        System.out.println(bridge(str, x -> x.toUpperCase()));
        System.out.println(bridge(str, x -> x.substring(2, 4)));
    }

    public String bridge(String str, MyFun2 mf) {
        return mf.getValue(str);
    }

    /**
     * （1）声明一个带两个泛型的函数式接口，泛型类型为<T,R> : T 为参数，R 为返回值。
     * （2）接口中声明对应抽象方法
     * （3）在LambdaTest类中声明方法，使用接口作为参数，计算两个long型参数的和。
     * （4）再计算两个long型参数的乘积
     */
    @Test
    public void test3() {
        Long num1 = 10l;
        Long num2 = 15l;
        System.out.println(bridge2(num1, num2, (x, y) -> x + y));
        System.out.println(bridge2(num1, num2, (x, y) -> x * y));
    }

    public Long bridge2(Long num1, Long num2, MyFun3<Long, Long> mf) {
        return mf.operation(num1, num2);
    }

    /**
     *
     */
    @Test
    public void test4() {

    }


}
