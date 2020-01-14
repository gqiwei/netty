package netty.server.packet;

import lombok.Data;
import netty.Command;
import netty.Packet;
import netty.session.Session;

import java.util.List;


/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {
    private int code ;
    private String message;
    private String groupId;
    private List<Session> sessionList;
    @Override
    public Byte getCommand() {
        return Command.LISTGROUPMEMBERS_RESPONSE;
    }
}
