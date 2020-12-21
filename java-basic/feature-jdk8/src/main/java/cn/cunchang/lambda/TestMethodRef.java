package cn.cunchang.lambda;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/*
 * 一、方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用“方法引用”（可以理解为方法引用时Lambda表达式的另一种表现形式）
 *
 * 主要有三种语法格式：
 * 对象::实例方法名
 * 类::静态方法名
 * 类::实例方法名
 *
 * 注意：
 * 1、Lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致！
 * 2、若Lambda参数列表中的第一个参数是 实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method
 *
 * 二、构造器引用:
 * 格式：
 * ClassName::new
 *
 * 注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致！
 *
 * 三：数组引用：
 *  Type::new;
 *
 */
public class TestMethodRef {

    // 对象::实例方法名
    @Test
    public void test1() {
        PrintStream ps = System.out;
        Consumer<String> con = x -> ps.println(x);//生成了一个实现了Consumer接口的类的对象

        PrintStream ps1 = System.out;
        Consumer<String> con1 = ps1::println;//相当于上面，引用了ps对象的println()方法

        Consumer<String> con2 = System.out::println;
        con2.accept("abcde");
    }

    @Test
    public void test2() {
        Employee employee = new Employee();
        Supplier<String> sup = () -> employee.getName();//代替匿名内部类
        System.out.println(sup.get());

        Supplier<String> sup2 = employee::getName;
        System.out.println(sup2.get());
    }

    //类::静态方法名
    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> com1 = Integer::compare;
    }

    //类::实例方法名
    @Test
    public void test4() {
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
        BiPredicate<String, String> bp2 = String::equals;
    }

    // 构造器引用
    @Test
    public void test5() {
        Supplier<Employee> sup = () -> new Employee();

        // 构造器引用
        Supplier<Employee> sup2 = Employee::new;
        System.out.println(sup2.get());

        Function<Integer, Employee> fun2 = (x) -> new Employee(x);
        System.out.println(fun2.apply(101));

        BiFunction<String, Integer, Employee> bf = Employee::new;
    }

    // 数组引用
    @Test
    public void test6() {
        Function<Integer, String[]> fun = (x) -> new String[x];
        System.out.println(fun.apply(10).length);

        Function<Integer,String[]> fun2=String[]::new;
        String[] str2=fun2.apply(20);
        System.out.println(str2.length);
    }


}
