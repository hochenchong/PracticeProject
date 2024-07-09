package behavioral.strategy;

import utils.XMLTagNameConstant;
import utils.XMLUtil;

/**
 * @author hochenchong
 * @date 2024/07/08
 */
public class StrategyClient {
    public static void main(String[] args) {
        MovieTicket movieTicket = new MovieTicket();
        double originalPrice = 60.0;

        movieTicket.setPrice(originalPrice);
        System.out.println("原始价格为：" + originalPrice);
        System.out.println("----------");

        Discount discount = (Discount) XMLUtil.getBean(XMLTagNameConstant.STRATEGY);
        movieTicket.setDiscount(discount);

        double currentPrice = movieTicket.getPrice();
        System.out.println("折后价格为：" + currentPrice);
    }
}
