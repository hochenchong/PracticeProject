package behavioral.chainOfResponsibility;

/**
 * 董事长类
 *
 * @author hochenchong
 * @date 2024/07/07
 */
public class President extends Approver {
    public President(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        System.out.println("董事长 '" + this.name + "' 审批采购单：" + request.getNum()
                + "，金额：" + request.getAmount() + "元，采购目的：" + request.getPurpose());
    }
}
