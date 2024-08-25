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
 * @date 2024/08/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ListGroupMembersRespPacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @JsonIgnore
    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESP;
    }
}
