package wawa.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-15
 */
public class SendClient {

    public static void sendWawa(ChannelHandlerContext ctx){
        int arrow = 3;//爪子往右
        int ms = 0;
        ByteBuf byteBuf = ctx.alloc().ioBuffer();
        int length = 0x0c;
        Parser.WriteHead(byteBuf,length);
        byteBuf.writeByte(0x32);

        byteBuf.writeByte(arrow);

        byteBuf.writeByte(ms%256);

        byteBuf.writeByte(ms/256);

        Parser.SetCheckSum(byteBuf);

        ctx.writeAndFlush(byteBuf);
    }

}
