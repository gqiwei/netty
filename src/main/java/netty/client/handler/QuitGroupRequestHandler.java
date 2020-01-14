package netty.client.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.client.packet.QuitGroupRequestPacket;
import netty.server.packet.JoinGroupResponsePacket;
import netty.server.packet.QuitGroupResponsePacket;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
        System.out.println(quitGroupResponsePacket.getMessage());
    }
}
