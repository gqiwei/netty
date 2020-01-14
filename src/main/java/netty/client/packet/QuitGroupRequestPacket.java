package netty.client.packet;

import lombok.Data;
import netty.Command;
import netty.Packet;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
@Data
public class QuitGroupRequestPacket extends Packet {
    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.QUITGROUP_REQUEST;
    }
}
