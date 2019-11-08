package cn.lastwhisper.jdk8.lambda.strategy;

import cn.lastwhisper.jdk8.lambda.Employee;

/**
 * @author lastwhisper
 */
public class EmployeeSalaryFilter implements Filter<Employee> {
    @Override
    public boolean eqCondition(Employee employee) {
        return employee.getSalary() >= 2000;
    }
}
