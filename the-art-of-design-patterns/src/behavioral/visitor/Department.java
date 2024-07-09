package behavioral.visitor;

/**
 * 抽象部门类：抽象访问者类
 *
 * @author hochenchong
 * @date 2024/7/8
 */
public abstract class Department {
    public abstract void visit(FullTimeEmployee employee);
    public abstract void visit(PartTimeEmployee employee);
}
