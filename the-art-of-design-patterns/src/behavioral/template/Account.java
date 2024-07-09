package behavioral.template;

/**
 * 账户类：抽象类
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public abstract class Account {
    // 基本方法-具体方法
    public boolean validate(String account, String password) {
        System.out.println("账号：" + account);
        System.out.println("密码：" + password);
        if ("张三".equalsIgnoreCase(account) && "123456".equalsIgnoreCase(password)) {
            return true;
        }
        return false;
    }

    // 抽象方法
    public abstract void calculateInterest();

    public void display() {
        System.out.println("显示利息");
    }

    // 模板方法
    public void handle(String account, String password) {
        if (!validate(account, password)) {
            System.out.println("账号或密码错误！");
            return;
        }
        calculateInterest();
        display();
    }
}
