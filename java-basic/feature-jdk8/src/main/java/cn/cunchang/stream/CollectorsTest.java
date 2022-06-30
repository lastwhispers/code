package cn.cunchang.stream;

import cn.cunchang.lambda.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author cunchang
 * @date 2022/5/25 6:02 PM
 */
public class CollectorsTest {

    static List<Employee> employees = Arrays.asList(
            new cn.cunchang.lambda.Employee("张三", 18, 9999.99),
            new cn.cunchang.lambda.Employee("张三", 58, 5555.55));

    public static void main(String[] args) {
        Map<String, Employee> map = employees.stream()
                .collect(Collectors.toMap(Employee::getName, employee -> employee));
        // merge 策略
//        Map<String, Employee> map = employees.stream()
//                .collect(Collectors.toMap(Employee::getName, employee -> employee, (t, t2) -> t));
        System.out.println(map);
    }


}
