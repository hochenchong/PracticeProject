package behavioral.visitor;

/**
 * 全职员工类
 *
 * @author hochenchong
 * @date 2024/7/8
 */
public class FullTimeEmployee implements Employee {
    // 姓名
    private String name;
    // 周薪
    private double weeklyWage;
    // 工作时间
    private int workTime;

    public FullTimeEmployee(String name, double weeklyWage, int workTime) {
        this.name = name;
        this.weeklyWage = weeklyWage;
        this.workTime = workTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeeklyWage() {
        return weeklyWage;
    }

    public void setWeeklyWage(double weeklyWage) {
        this.weeklyWage = weeklyWage;
    }

    public int getWorkTime() {
        return workTime;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }

    @Override
    public void accept(Department department) {
        // 调用访问者的访问方法
        department.visit(this);
    }
}
