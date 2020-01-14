package netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.server.packet.GroupMessageResponsePacket;
import netty.session.Session;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-14
 */
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMessageResponsePacket groupMessageResponsePacket) throws Exception {
        Session fromUser = groupMessageResponsePacket.getFromUser();
        String message = groupMessageResponsePacket.getMessage();
        String groupId = groupMessageResponsePacket.getGroupId();

        System.out.println("群组`"+groupId+"`的消息：["+fromUser.getUserName()+"]---> "+message);
    }
}
