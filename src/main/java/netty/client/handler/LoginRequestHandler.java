package netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.client.packet.LoginRequestPacket;
import netty.server.packet.LoginResponsePacket;
import netty.session.Session;
import netty.util.SessionUtil;

import java.util.UUID;


/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-09
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    public static final LoginRequestHandler INSTANCE= new LoginRequestHandler();


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        if(loginResponsePacket.getCode()==1){
            SessionUtil.bindSession(new Session(loginResponsePacket.getUserId(),loginResponsePacket.getUserName()),channelHandlerContext.channel());
        }
        System.out.println(loginResponsePacket.getMessage()+"----- "+loginResponsePacket.getUserId());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接被关闭!");
    }
}
