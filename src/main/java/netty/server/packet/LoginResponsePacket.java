package netty.server.packet;

import lombok.Data;
import netty.Packet;

import static netty.Command.LOGIN_RESPONSE;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-08
 */
@Data
public class LoginResponsePacket extends Packet {
    private String userId;
    private String userName;
    private String message ;
    private int code;
    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
