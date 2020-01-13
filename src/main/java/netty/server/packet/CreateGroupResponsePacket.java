package netty.server.packet;

import lombok.Data;
import netty.Packet;

import java.util.List;

import static netty.Command.CREATEGROUP_RESPONSE;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
@Data
public class CreateGroupResponsePacket extends Packet {
    private int code;
    private String message;
    private String groupId;
    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return CREATEGROUP_RESPONSE;
    }
}
