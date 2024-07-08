package behavioral.state;

/**
 * 透支状态类：具体状态类
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class OverdraftState extends AccountState {
    public OverdraftState(Account account) {
        this.account = account;
    }

    @Override
    public void deposit(double amount) {
        account.setBalance(account.getBalance() + amount);
        stateCheck();
    }

    @Override
    public void withdraw(double amount) {
        account.setBalance(account.getBalance() - amount);
        stateCheck();
    }

    @Override
    public void computeInterest() {
        System.out.println("计算利息！");
    }

    @Override
    public void stateCheck() {
        if (account.getBalance() > 0) {
            account.setState(new NormalState(this.account));
        } else if (account.getBalance() == -2000) {
            account.setState(new RestrictedState(this.account));
        } else if (account.getBalance() < -2000) {
            System.out.println("操作受限！");
        }
    }
}
