package netty.server.handler;

import io.netty.channel.ChannelHandler;
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
@ChannelHandler.Sharable
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    public static final QuitGroupResponseHandler INSTANCE = new QuitGroupResponseHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
        String groupId = quitGroupRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();
        if(channelGroup!=null){
            channelGroup.remove(channelHandlerContext.channel());
            if(channelGroup.size()==0){
                SessionUtil.unBindChannelGroup(groupId);
            }
            quitGroupResponsePacket.setCode(1);
            quitGroupResponsePacket.setGroupId(groupId);
            quitGroupResponsePacket.setMessage("成功退出群组`"+groupId+"`");
        }else{
            quitGroupResponsePacket.setCode(2);
            quitGroupResponsePacket.setGroupId(groupId);
            quitGroupResponsePacket.setMessage("`"+groupId+"`群组不存在");
        }
        channelHandlerContext.channel().writeAndFlush(quitGroupResponsePacket);
    }
}
