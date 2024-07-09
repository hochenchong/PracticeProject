package behavioral.state;

/**
 * 状态模式客户端
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class StateClient {

    public static void main(String[] args) {
        Account account = new Account("张三", 0.0);
        account.deposit(1000);
        account.withdraw(2000);
        account.deposit(3000);
        account.withdraw(4000);
        account.withdraw(1000);
        account.computeInterest();
    }
}
