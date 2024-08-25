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
public class QuitGroupReqPacket extends Packet {
    /**
     * 要退出的群聊 id
     */
    private String groupId;

    @JsonIgnore
    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_REQ;
    }
}
