package netty.client.packet;

import lombok.Data;
import netty.Packet;

import static netty.Command.LOGIN_REQUEST;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-08
 */
@Data
public class LoginRequestPacket extends Packet {
    private String userId;
    private String username;
    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
