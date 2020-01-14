package netty.server.packet;

import lombok.Data;
import netty.Command;
import netty.Packet;
import netty.session.Session;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-14
 */
@Data
public class GroupMessageResponsePacket extends Packet {
    private Session fromUser ;
    private String message;
    private String groupId;
    @Override
    public Byte getCommand() {
        return Command.GROUPMESSAGE_RESPONSE;
    }
}
