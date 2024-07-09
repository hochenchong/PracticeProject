package behavioral.strategy.support;

import behavioral.strategy.Discount;

/**
 * 儿童票折扣类：具体策略类
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class ChildrenDiscount implements Discount {
    @Override
    public double calculate(double price) {
        System.out.println("儿童票：");
        if (price >= 20) {
            return price - 10;
        }
        return price;
    }
}
