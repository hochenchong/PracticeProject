package behavioral.chainOfResponsibility;

/**
 * 经理类
 *
 * @author hochenchong
 * @date 2024/07/07
 */
public class Manager extends Approver {
    public Manager(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < 100000) {
            System.out.println("经理 '" + this.name + "' 审批采购单：" + request.getNum()
                    + "，金额：" + request.getAmount() + "元，采购目的：" + request.getPurpose());
        } else {
            this.successor.processRequest(request);
        }
    }
}
