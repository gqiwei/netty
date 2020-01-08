package netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.Packet;
import netty.PacketCodec;
import netty.client.packet.LoginRequestPacket;
import netty.server.packet.LoginResponsePacket;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-08
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx){
//        ByteBuf byteBuf = ctx.alloc().buffer();
//        byteBuf.writeBytes("我要连接服务器".getBytes(Charset.forName("utf-8")));
//        ctx.writeAndFlush(byteBuf);

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setPassword("123456");
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("gqw");

        ByteBuf byteBuf= PacketCodec.INSTANCE.encode(loginRequestPacket); //信息编码
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg){
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodec.INSTANCE.decode(byteBuf);
        if(packet instanceof LoginResponsePacket){
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            System.out.println(loginResponsePacket.getMessage());

        }
    }
}
