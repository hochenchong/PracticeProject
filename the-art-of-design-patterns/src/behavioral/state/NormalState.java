package behavioral.state;

/**
 * 正常状态类：具体状态类
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class NormalState extends AccountState {

    public NormalState(Account account) {
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
        System.out.println("正常状态，无须支付利息！");
    }

    @Override
    public void stateCheck() {
        if (account.getBalance() > -2000 && account.getBalance() <= 0) {
            account.setState(new OverdraftState(this.account));
        } else if (account.getBalance() == -2000) {
            account.setState(new RestrictedState(this.account));
        } else if (account.getBalance() < -2000) {
            System.out.println("操作受限！");
        }
    }
}
