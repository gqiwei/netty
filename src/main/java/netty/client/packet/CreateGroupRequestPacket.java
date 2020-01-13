package netty.client.packet;

import lombok.Data;
import netty.Packet;

import java.util.List;

import static netty.Command.CREATEGROUP_REQUEST;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
@Data
public class CreateGroupRequestPacket extends Packet {
    private List<String>userIdList;

    @Override
    public Byte getCommand() {
        return CREATEGROUP_REQUEST;
    }
}
