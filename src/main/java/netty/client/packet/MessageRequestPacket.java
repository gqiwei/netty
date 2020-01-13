package netty.client.packet;

import lombok.Data;
import netty.Packet;

import static netty.Command.MESSAGE_REQUEST;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-08
 */
@Data
public class MessageRequestPacket extends Packet {
    private String userId;
    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
