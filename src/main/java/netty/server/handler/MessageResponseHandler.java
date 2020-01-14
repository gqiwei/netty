package netty.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.PacketCodec;
import netty.client.packet.MessageRequestPacket;
import netty.server.packet.MessageResponsePacket;
import netty.session.Session;
import netty.util.SessionUtil;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-09
 */
@ChannelHandler.Sharable
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    public static final MessageResponseHandler INSTANCE = new MessageResponseHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {

        Session sendSession = SessionUtil.getSession(ctx.channel());//发送方信息

        String userId = messageRequestPacket.getUserId();//接收方id

        System.out.println("来自客户端的消息："+messageRequestPacket.getMessage());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();

        Channel toChannel = SessionUtil.getChannel(userId);

        if(toChannel!=null && SessionUtil.hasLogin(toChannel)){
            messageResponsePacket.setFromUserId(sendSession.getUserId());
            messageResponsePacket.setMessage("来自`"+sendSession.getUserName()+"`的消息： "+messageRequestPacket.getMessage());
            toChannel.writeAndFlush(messageResponsePacket);
        }else{
            messageResponsePacket.setFromUserId("system");
            messageResponsePacket.setMessage("当前联系用户不在线，请稍后尝试！");
            ctx.channel().writeAndFlush(messageResponsePacket);
        }

    }
}
