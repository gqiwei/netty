package netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import netty.client.packet.JoinGroupRequestPacket;
import netty.server.packet.JoinGroupResponsePacket;
import netty.util.SessionUtil;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
        String groupId = joinGroupRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
        if(channelGroup!=null){
            channelGroup.add(channelHandlerContext.channel());
            joinGroupResponsePacket.setCode(1);
            joinGroupResponsePacket.setGroupId(groupId);
            joinGroupResponsePacket.setMessage("成功加入群组`"+groupId+"`");
        }else{
            joinGroupResponsePacket.setCode(2);
            joinGroupResponsePacket.setGroupId(groupId);
            joinGroupResponsePacket.setMessage("`"+groupId+"`群组不存在");
        }


        channelHandlerContext.channel().writeAndFlush(joinGroupResponsePacket);
    }
}
