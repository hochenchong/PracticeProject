package behavioral.strategy.support;

import behavioral.strategy.Discount;

/**
 * VIP 会员折扣票：具体策略类
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class VIPDiscount implements Discount {
    @Override
    public double calculate(double price) {
        System.out.println("VIP 票：");
        System.out.println("增加积分！");
        return price * 0.5;
    }
}
