package netty.server.packet;

import lombok.Data;
import netty.Packet;

import static netty.Command.JOINGROUP_RESPONSE;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
@Data
public class JoinGroupResponsePacket extends Packet {
    private int code ;
    private String message;
    private String groupId;
    @Override
    public Byte getCommand() {
        return JOINGROUP_RESPONSE;
    }
}
