package behavioral.template;

import utils.XMLTagNameConstant;
import utils.XMLUtil;

/**
 * 模板方法模式客户端
 *
 * @author hochenchong
 * @date 2024/07/08
 */
public class TemplateClient {
    public static void main(String[] args) {
        Account account = (Account) XMLUtil.getBean(XMLTagNameConstant.TEMPLATE);
        assert account != null;
        account.handle("张三", "123456");
        System.out.println("--------");
        account.handle("张三", "12345");
    }
}
