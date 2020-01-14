package netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import netty.client.packet.GroupMessageRequestPacket;
import netty.server.packet.GroupMessageResponsePacket;
import netty.session.Session;
import netty.util.SessionUtil;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-14
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    public static final GroupMessageResponseHandler INSTANCE = new GroupMessageResponseHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMessageRequestPacket groupMessageRequestPacket) throws Exception {
        String groupId = groupMessageRequestPacket.getGroupId();
        String message =groupMessageRequestPacket.getMessage();
        Session fromUser = SessionUtil.getSession(channelHandlerContext.channel());

        GroupMessageResponsePacket groupMessageResponsePacket = new GroupMessageResponsePacket();
        groupMessageResponsePacket.setFromUser(fromUser);
        groupMessageResponsePacket.setGroupId(groupId);
        groupMessageResponsePacket.setMessage(message);

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        channelGroup.writeAndFlush(groupMessageResponsePacket);
    }
}
