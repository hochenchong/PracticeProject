package behavioral.visitor;

import utils.XMLTagNameConstant;
import utils.XMLUtil;

/**
 * @author hochenchong
 * @date 2024/07/08
 */
public class VisitorClient {
    public static void main(String[] args) {
        Department department = (Department) XMLUtil.getBean(XMLTagNameConstant.VISITOR);

        EmployeeList list = new EmployeeList();
        list.addEmployee(new FullTimeEmployee("张三", 3200.00, 45));
        list.addEmployee(new FullTimeEmployee("李四", 2000.00, 40));
        list.addEmployee(new FullTimeEmployee("王五", 2400.00, 38));
        list.addEmployee(new PartTimeEmployee("赵六", 80.00, 20));
        list.addEmployee(new PartTimeEmployee("孙七", 60.00, 18));

        list.accept(department);
    }
}
