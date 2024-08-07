package behavioral.strategy;

/**
 * @author hochenchong
 * @date 2024/07/08
 */
public class MovieTicket {
    private double price;
    private Discount discount;

    public double getPrice() {
        return discount.calculate(this.price);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
