package netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.PacketCodec;
import netty.client.packet.LoginRequestPacket;
import netty.server.handler.LoginResponseHandler;
import netty.server.packet.LoginResponsePacket;

import java.util.UUID;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-09
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    public void channelActive(ChannelHandlerContext ctx){
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setPassword("123456");
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("gqw");
        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        System.out.println(loginResponsePacket.getMessage());
    }
}
