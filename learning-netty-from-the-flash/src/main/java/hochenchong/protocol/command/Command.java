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
    Byte LOGIN_REQ = 1;
    /**
     * 登录响应
     */
    Byte LOGIN_RESP = 2;
    /**
     * 消息请求
     */
    Byte MSG_REQ = 3;
    /**
     * 消息响应
     */
    Byte MSG_RESP = 4;

    /**
     * 创建群聊
     */
    Byte CREATE_GROUP_REQ = 5;
    /**
     * 创建群聊响应
     */
    Byte CREATE_GROUP_RESP = 6;
}
