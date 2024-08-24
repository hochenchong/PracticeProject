package hochenchong.protocol.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hochenchong.protocol.Packet;
import hochenchong.protocol.command.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息响应
 *
 * @author hochenchong
 * @date 2024/08/22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MsgRespPacket extends Packet {
    private String fromUserId;

    private String fromUsername;

    private String message;

    @JsonIgnore
    @Override
    public byte getCommand() {
        return Command.MSG_RESP;
    }
}
