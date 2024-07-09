package behavioral.visitor;

/**
 * 抽象员工类
 * @author hochenchong
 * @date 2024/7/8
 */
public interface Employee {
    // 抽象访问者访问
    public void accept(Department department);
}
