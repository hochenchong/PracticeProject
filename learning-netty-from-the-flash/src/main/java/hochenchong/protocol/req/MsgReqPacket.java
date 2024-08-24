package hochenchong.protocol.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hochenchong.protocol.command.Command;
import hochenchong.protocol.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息请求
 *
 * @author hochenchong
 * @date 2024/08/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MsgReqPacket extends Packet {
    /**
     * 发送给哪个用户
     */
    private String toUserId;
    private String message;

    @JsonIgnore
    @Override
    public byte getCommand() {
        return Command.MSG_REQ;
    }
}
