package netty.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import netty.client.packet.CreateGroupRequestPacket;
import netty.server.packet.CreateGroupResponsePacket;
import netty.session.Session;
import netty.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
@ChannelHandler.Sharable
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    public static final CreateGroupResponseHandler INSTANCE = new CreateGroupResponseHandler();
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> userIdList = createGroupRequestPacket.getUserIdList();
        List<String> userNameList = new ArrayList<String>();

        ChannelGroup channelGroup =new DefaultChannelGroup(channelHandlerContext.executor());
        for(String userId:userIdList){
            Channel channel =SessionUtil.getChannel(userId);
            if(channel != null){
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        }

        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        String groupId =UUID.randomUUID().toString();
        createGroupResponsePacket.setCode(1);
        createGroupResponsePacket.setMessage("`"+groupId+"`群组创建成功");
        createGroupResponsePacket.setUserNameList(userNameList);
        createGroupResponsePacket.setGroupId(groupId);

        SessionUtil.bindChannelGroup(groupId,channelGroup);

        channelGroup.writeAndFlush(createGroupResponsePacket);

        System.out.println("群`"+createGroupResponsePacket.getGroupId()+"`创建成功。");
        System.out.println("组员："+createGroupResponsePacket.getUserNameList());

    }
}
