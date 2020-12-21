package cn.cunchang.lambda.strategy;

import cn.cunchang.lambda.Employee;

/**
 * @author lastwhisper
 */
public class EmployeeAgeFilter implements Filter<Employee> {
    @Override
    public boolean eqCondition(Employee employee) {
        return employee.getAge() >= 35;
    }
}
