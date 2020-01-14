package netty.client.packet;

import lombok.Data;
import netty.Packet;

import static netty.Command.JOINGROUP_REQUEST;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
@Data
public class JoinGroupRequestPacket extends Packet {
    private String groupId;

    @Override
    public Byte getCommand() {
        return JOINGROUP_REQUEST;
    }
}
