package behavioral.visitor;

/**
 * 兼职员工类
 *
 * @author hochenchong
 * @date 2024/7/8
 */
public class PartTimeEmployee implements Employee {
    // 姓名
    private String name;
    // 周薪
    private double hourWage;
    // 工作时间
    private int workTime;

    public PartTimeEmployee(String name, double weeklyWage, int workTime) {
        this.name = name;
        this.hourWage = weeklyWage;
        this.workTime = workTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHourWage() {
        return hourWage;
    }

    public void setHourWage(double hourWage) {
        this.hourWage = hourWage;
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
