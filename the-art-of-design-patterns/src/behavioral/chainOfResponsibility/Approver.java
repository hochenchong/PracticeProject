package behavioral.chainOfResponsibility;

/**
 * 抽象审批者类
 *
 * @author hochenchong
 * @date 2024/07/07
 */
public abstract class Approver {
    // 定义后继对象
    protected Approver successor;
    // 审批者姓名
    protected String name;

    public Approver(String name) {
        this.name = name;
    }

    public void setSuccessor(Approver successor) {
        this.successor = successor;
    }

    // 抽象请求方法
    public abstract void processRequest(PurchaseRequest request);
}
