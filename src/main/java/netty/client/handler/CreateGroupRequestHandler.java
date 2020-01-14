package netty.client.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.server.packet.CreateGroupResponsePacket;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        System.out.println(createGroupResponsePacket.getMessage());
    }
}
