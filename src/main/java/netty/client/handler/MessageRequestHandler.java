package netty.client.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.client.packet.MessageRequestPacket;
import netty.server.packet.MessageResponsePacket;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-09
 */
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
        System.out.println("来自服务端的消息----> "+messageResponsePacket.getMessage());
    }
}
