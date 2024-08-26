package hochenchong.protocol.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hochenchong.chapter.chapter16.Session;
import hochenchong.protocol.Packet;
import hochenchong.protocol.command.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author hochenchong
 * @date 2024/08/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMsgRespPacket extends Packet {

    private String groupId;

    private String msg;

    private Session fromUser;

    @JsonIgnore
    @Override
    public Byte getCommand() {
        return Command.GROUP_MSG_RESP;
    }
}
