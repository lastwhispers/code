package cn.cunchang.lambda.strategy;

import cn.cunchang.lambda.Employee;

/**
 * @author lastwhisper
 */
public class EmployeeSalaryFilter implements Filter<Employee> {
    @Override
    public boolean eqCondition(Employee employee) {
        return employee.getSalary() >= 2000;
    }
}
