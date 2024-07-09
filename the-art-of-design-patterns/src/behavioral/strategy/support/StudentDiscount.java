package behavioral.strategy.support;

import behavioral.strategy.Discount;

/**
 * 学生票折扣类：具体策略类
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class StudentDiscount implements Discount {
    @Override
    public double calculate(double price) {
        System.out.println("学生票：");
        return price * 0.8;
    }
}
