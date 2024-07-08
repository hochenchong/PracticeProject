package behavioral.template.support;

import behavioral.template.Account;

/**
 * 定期账户类：具体子类
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class SavingAccount extends Account {
    @Override
    public void calculateInterest() {
        System.out.println("按定期利率计算利息！");
    }
}
