package behavioral.template.support;

import behavioral.template.Account;

/**
 * 活期账户类：具体子类
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class CurrentAccount extends Account {
    @Override
    public void calculateInterest() {
        System.out.println("按活期利率计算利息！");
    }
}
