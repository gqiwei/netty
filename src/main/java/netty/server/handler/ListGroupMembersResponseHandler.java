package netty.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import netty.client.packet.ListGroupMembersRequestPacket;
import netty.client.packet.QuitGroupRequestPacket;
import netty.server.packet.ListGroupMembersResponsePacket;
import netty.server.packet.QuitGroupResponsePacket;
import netty.session.Session;
import netty.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListGroupMembersRequestPacket listGroupMembersRequestPacket) throws Exception {
        String groupId = listGroupMembersRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);


        List<Session> sessionList = new ArrayList<Session>();
        for(Channel channel: channelGroup){
            Session session =SessionUtil.getSession(channel);
            sessionList.add(session);
        }

        ListGroupMembersResponsePacket listGroupMembersResponsePacket = new ListGroupMembersResponsePacket();
        listGroupMembersResponsePacket.setCode(1);
        listGroupMembersResponsePacket.setGroupId(groupId);
        listGroupMembersResponsePacket.setMessage("查询成功");
        listGroupMembersResponsePacket.setSessionList(sessionList);

        channelHandlerContext.channel().writeAndFlush(listGroupMembersResponsePacket);
    }
}
