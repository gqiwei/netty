package netty.client.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.server.packet.JoinGroupResponsePacket;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    public static final JoinGroupRequestHandler INSTANCE= new JoinGroupRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, JoinGroupResponsePacket joinGroupResponsePacket) throws Exception {
        System.out.println(joinGroupResponsePacket.getMessage());
    }
}
