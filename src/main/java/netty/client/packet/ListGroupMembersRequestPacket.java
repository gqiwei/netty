package netty.client.packet;

import lombok.Data;
import netty.Command;
import netty.Packet;

import static netty.Command.JOINGROUP_REQUEST;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
@Data
public class ListGroupMembersRequestPacket extends Packet {
    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.LISTGROUPMEMBERS_REQUEST;
    }
}
