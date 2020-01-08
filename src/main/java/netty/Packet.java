package netty;

import lombok.Data;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-08
 */
@Data
public abstract class Packet {
    private Byte version =1;

    public abstract Byte getCommand();
}
