package hochenchong.protocol.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hochenchong.protocol.Packet;
import hochenchong.protocol.command.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hochenchong
 * @date 2024/08/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JoinGroupReqPacket extends Packet {
    /**
     * 要加入的群聊 id
     */
    private String groupId;

    @JsonIgnore
    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_REQ;
    }
}
