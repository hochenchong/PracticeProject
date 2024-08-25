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
    /**
     * 加入群聊请求
     */
    Byte JOIN_GROUP_REQ = 7;
    /**
     * 加入群聊响应
     */
    Byte JOIN_GROUP_RESP = 8;
    /**
     * 退出群聊
     */
    Byte QUIT_GROUP_REQ = 9;
    /**
     * 退出群聊响应
     */
    Byte QUIT_GROUP_RESP = 10;
    /**
     * 查看群成员请求
     */
    Byte LIST_GROUP_MEMBERS_REQ = 11;
    /**
     * 查看群成员响应
     */
    Byte LIST_GROUP_MEMBERS_RESP = 12;

}
