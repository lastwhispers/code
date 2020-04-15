package cn.lastwhisper.feature8.lambda.strategy;

import cn.lastwhisper.feature8.lambda.Employee;

/**
 * @author lastwhisper
 */
public class EmployeeAgeFilter implements Filter<Employee> {
    @Override
    public boolean eqCondition(Employee employee) {
        return employee.getAge() >= 35;
    }
}
