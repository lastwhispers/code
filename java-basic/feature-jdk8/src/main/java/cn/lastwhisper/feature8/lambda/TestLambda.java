package cn.lastwhisper.feature8.lambda;

import cn.lastwhisper.feature8.lambda.strategy.EmployeeAgeFilter;
import cn.lastwhisper.feature8.lambda.strategy.EmployeeSalaryFilter;
import cn.lastwhisper.feature8.lambda.strategy.Filter;
import org.junit.Test;

import java.util.*;

/**
 * @author lastwhisper
 */
public class TestLambda {
    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9496.2),
            new Employee("李四", 52, 2396.2),
            new Employee("王五", 56, 996.2),
            new Employee("赵六", 38, 940.2)
    );

    // 原来匿名内部类
    @Test
    public void testInnerClass() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        };

        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    // 将匿名内部类优化为lambda表达式
    @Test
    public void testLambda() {
        Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    // 传统方式进行条件过滤
    @Test
    public void test3() {
        //需求：获取当前公司中员工年龄大于35的员工信息
        List<Employee> emps = filterEmployeesByAge(employees);
        for (Employee e : emps) {
            System.out.println(e);
        }
        System.out.println("---------------------");

        //需求：获取当前公司中员工工资大于2000的员工信息
        List<Employee> emps2 = filterEmployeesBySalary(employees);
        for (Employee e : emps2) {
            System.out.println(e);
        }
    }

    public List<Employee> filterEmployeesByAge(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();
        for (Employee emp : list) {
            if (emp.getAge() >= 35) {
                emps.add(emp);
            }
        }
        return emps;
    }

    public List<Employee> filterEmployeesBySalary(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();
        for (Employee emp : list) {
            if (emp.getSalary() >= 2000) {
                emps.add(emp);
            }
        }
        return emps;
    }

    // test3 优化方式一：策略设计模式
    @Test
    public void test4() {
        List<Employee> emps = filterEmployees(employees, new EmployeeAgeFilter());
        for (Employee e : emps) {
            System.out.println(e);
        }
        System.out.println("---------------------");
        List<Employee> emps2 = filterEmployees(employees, new EmployeeSalaryFilter());
        for (Employee e : emps2) {
            System.out.println(e);
        }
    }

    public List<Employee> filterEmployees(List<Employee> list, Filter<Employee> filter) {
        List<Employee> emps = new ArrayList<Employee>();
        for (Employee emp : list) {
            if (filter.eqCondition(emp)) {
                emps.add(emp);
            }
        }
        return emps;
    }

    // test3 优化方式二：匿名内部类
    @Test
    public void test5() {
        List<Employee> list = filterEmployees(employees, new Filter<Employee>() {
            @Override
            public boolean eqCondition(Employee t) {
                return t.getSalary() >= 2000;
            }
        });
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    // test3 优化方式三：Lambda表达式
    @Test
    public void test6() {
        List<Employee> list = filterEmployees(employees, (e) -> e.getSalary() >= 2000);
        list.forEach(System.out::println);
    }

    // test3 优化方式四：stream API
    @Test
    public void test7(){
        employees.stream()
                .filter((e)->e.getSalary()>=2000)
                .forEach(System.out::println);
        System.out.println("------------------");
        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }



}
