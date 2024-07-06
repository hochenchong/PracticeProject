package structural.proxy;

/**
 * 身份验证类
 *
 * @author hochenchong
 * @date 2024/07/06
 */
public class AccessValidator {
    public boolean validate(String userId) {
        System.out.println("在数据库中验证用户 '" + userId + "' 是否为合法用户");
        if ("张三".equals(userId)) {
            System.out.println("'" + userId + "' 校验成功！");
            return true;
        }
        System.out.println("'" + userId + "' 校验失败！");
        return false;
    }
}
