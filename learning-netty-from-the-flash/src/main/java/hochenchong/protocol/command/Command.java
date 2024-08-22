package hochenchong.protocol.command;

/**
 * 命令
 *
 * @author hochenchong
 * @date 2024/08/22
 */
public interface Command {
    /**
     * 登录请求
     */
    byte LOGIN_REQ = 1;
    /**
     * 登录响应
     */
    byte LOGIN_RESP = 2;
    /**
     * 消息请求
     */
    byte MSG_REQ = 3;
    /**
     * 消息响应
     */
    byte MSG_RESP = 3;
}
