package netty.server.packet;

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
public class QuitGroupResponsePacket extends Packet {
    private int code ;
    private String message;
    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.QUITGROUP_RESPONSE;
    }
}
