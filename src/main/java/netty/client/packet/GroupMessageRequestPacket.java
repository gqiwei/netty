package netty.client.packet;

import lombok.Data;
import netty.Command;
import netty.Packet;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-14
 */
@Data
public class GroupMessageRequestPacket extends Packet {
    private String groupId;
    private String message;
    @Override
    public Byte getCommand() {
        return Command.GROUPMESSAGE_REQUEST;
    }
}
