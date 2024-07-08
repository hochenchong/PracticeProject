package behavioral.state;

/**
 * 抽象状态类
 * @author hochenchong
 * @date 2024/07/08
 */
public abstract class AccountState {
    protected Account account;

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    public abstract void computeInterest();

    public abstract void stateCheck();
}
