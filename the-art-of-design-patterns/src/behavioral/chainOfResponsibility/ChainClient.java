package behavioral.chainOfResponsibility;

/**
 * 职责链客户端
 *
 * @author hochenchong
 * @date 2024/07/07
 */
public class ChainClient {
    public static void main(String[] args) {
        Approver director = new Director("张三");
        Approver manager = new Manager("李四");
        Approver president = new President("王五");

        director.setSuccessor(manager);
        manager.setSuccessor(president);

        // 创建采购单
        PurchaseRequest pr1 = new PurchaseRequest(45000, 10001, "采购衣服");
        PurchaseRequest pr2 = new PurchaseRequest(60000, 10002, "采购办公桌");
        PurchaseRequest pr3 = new PurchaseRequest(110000, 10003, "采购电脑");
        director.processRequest(pr1);
        director.processRequest(pr2);
        director.processRequest(pr3);
    }
}
