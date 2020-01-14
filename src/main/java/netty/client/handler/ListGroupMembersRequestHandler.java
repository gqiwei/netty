package netty.client.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.server.packet.ListGroupMembersResponsePacket;
import netty.server.packet.QuitGroupResponsePacket;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
@ChannelHandler.Sharable
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {
    public static final ListGroupMembersRequestHandler INSTANCE= new ListGroupMembersRequestHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListGroupMembersResponsePacket listGroupMembersResponsePacket) throws Exception {
        if(listGroupMembersResponsePacket.getCode()==1){
            System.out.println("`"+listGroupMembersResponsePacket.getGroupId()+"`组中群员为："+ listGroupMembersResponsePacket.getSessionList());
        }else{
            System.out.println(listGroupMembersResponsePacket.getMessage());
        }

    }
}
