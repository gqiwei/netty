package netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import netty.client.packet.JoinGroupRequestPacket;
import netty.client.packet.QuitGroupRequestPacket;
import netty.server.packet.JoinGroupResponsePacket;
import netty.server.packet.QuitGroupResponsePacket;
import netty.util.SessionUtil;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
        String groupId = quitGroupRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.remove(channelHandlerContext.channel());

        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();
        quitGroupResponsePacket.setCode(1);
        quitGroupResponsePacket.setGroupId(groupId);
        quitGroupResponsePacket.setMessage("成功退出群组`"+groupId+"`");
        channelHandlerContext.channel().writeAndFlush(quitGroupResponsePacket);
    }
}
