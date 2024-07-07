package behavioral.chainOfResponsibility;

/**
 * 采购单：请求类
 *
 * @author hochenchong
 * @date 2024/07/07
 */
public class PurchaseRequest {
    // 采购金额
    private double amount;
    // 采购单编号
    private int num;
    // 采购目的
    private String purpose;

    public PurchaseRequest(double amount, int num, String purpose) {
        this.amount = amount;
        this.num = num;
        this.purpose = purpose;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
