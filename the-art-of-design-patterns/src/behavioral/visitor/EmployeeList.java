package behavioral.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hochenchong
 * @date 2024/7/8
 */
public class EmployeeList {
    private List<Employee> list = new ArrayList<>();

    public void addEmployee(Employee employee) {
        list.add(employee);
    }

    // 遍历访问员工集合的每一个员工对象
    public void accept(Department department) {
        for (Employee employee : list) {
            employee.accept(department);
        }
    }
}
