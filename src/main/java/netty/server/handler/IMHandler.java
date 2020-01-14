package netty.server.handler;

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
public class IMHandler extends SimpleChannelInboundHandler<Packet> {

    public static final IMHandler INSTANCE = new IMHandler();

    private Map<Byte,SimpleChannelInboundHandler<? extends Packet>> handlerMap ;

    public IMHandler(){
        handlerMap = new HashMap<>();
        handlerMap.put(Command.CREATEGROUP_RESPONSE,CreateGroupResponseHandler.INSTANCE);
        handlerMap.put(Command.JOINGROUP_RESPONSE,JoinGroupResponseHandler.INSTANCE);
        handlerMap.put(Command.LISTGROUPMEMBERS_RESPONSE,ListGroupMembersResponseHandler.INSTANCE);
        handlerMap.put(Command.MESSAGE_RESPONSE,MessageResponseHandler.INSTANCE);
        handlerMap.put(Command.QUITGROUP_RESPONSE,QuitGroupResponseHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(channelHandlerContext,packet);
    }
}
