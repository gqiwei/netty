package netty.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.Command;
import netty.Packet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-14
 */
@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<Packet> {

    public static final IMHandler INSTANCE = new IMHandler();

    private Map<Byte,SimpleChannelInboundHandler<? extends Packet>> handlerMap ;

    public IMHandler(){
        handlerMap = new HashMap<>();
        handlerMap.put(Command.CREATEGROUP_REQUEST,CreateGroupResponseHandler.INSTANCE);
        handlerMap.put(Command.JOINGROUP_REQUEST,JoinGroupResponseHandler.INSTANCE);
        handlerMap.put(Command.LISTGROUPMEMBERS_REQUEST,ListGroupMembersResponseHandler.INSTANCE);
        handlerMap.put(Command.MESSAGE_REQUEST,MessageResponseHandler.INSTANCE);
        handlerMap.put(Command.QUITGROUP_REQUEST,QuitGroupResponseHandler.INSTANCE);
        handlerMap.put(Command.GROUPMESSAGE_REQUEST,GroupMessageResponseHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(channelHandlerContext,packet);
    }
}
