package netty.server.packet;

import lombok.Data;
import netty.Packet;

import static netty.Command.MESSAGE_RESPONSE;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-08
 */
@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
