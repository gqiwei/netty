package netty.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
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
@ChannelHandler.Sharable
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {
    public static final ListGroupMembersResponseHandler INSTANCE = new ListGroupMembersResponseHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListGroupMembersRequestPacket listGroupMembersRequestPacket) throws Exception {
        String groupId = listGroupMembersRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        ListGroupMembersResponsePacket listGroupMembersResponsePacket = new ListGroupMembersResponsePacket();
        if(channelGroup!=null){
            List<Session> sessionList = new ArrayList<Session>();
            for(Channel channel: channelGroup){
                Session session =SessionUtil.getSession(channel);
                sessionList.add(session);
            }
            listGroupMembersResponsePacket.setCode(1);
            listGroupMembersResponsePacket.setGroupId(groupId);
            listGroupMembersResponsePacket.setMessage("查询成功");
            listGroupMembersResponsePacket.setSessionList(sessionList);
        }else{
            listGroupMembersResponsePacket.setCode(2);
            listGroupMembersResponsePacket.setGroupId(groupId);
            listGroupMembersResponsePacket.setMessage("`"+groupId+"`群组不存在");
        }

        channelHandlerContext.channel().writeAndFlush(listGroupMembersResponsePacket);
    }
}
