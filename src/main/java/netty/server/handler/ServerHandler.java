package netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.Packet;
import netty.PacketCodec;
import netty.attribute.Attributes;
import netty.client.packet.LoginRequestPacket;
import netty.client.packet.MessageRequestPacket;
import netty.server.packet.LoginResponsePacket;
import netty.server.packet.MessageResponsePacket;
import netty.util.LoginUtil;

import java.nio.charset.Charset;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-08
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg){
//        System.out.println(((ByteBuf)msg).toString(Charset.forName("utf-8")));

        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodec.INSTANCE.decode(byteBuf);



        if(packet instanceof LoginRequestPacket){ //判断消息体
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            if(valid(loginRequestPacket)){
                System.out.println(loginRequestPacket.getUsername()+"登录成功");
                LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
                loginResponsePacket.setCode(1);
                loginResponsePacket.setMessage("登陆成功");
                LoginUtil.markAsLogin(ctx.channel());
                ctx.channel().writeAndFlush(PacketCodec.INSTANCE.encode(loginResponsePacket));
            }
        }else if(packet instanceof MessageRequestPacket){
            if(LoginUtil.hasLogin(ctx.channel())){
                MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
                System.out.println("来自客户端的消息："+messageRequestPacket.getMessage());
                MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
                messageResponsePacket.setMessage("感谢您的发言！");
                ctx.channel().writeAndFlush(PacketCodec.INSTANCE.encode(messageResponsePacket));
            }
        }

    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx){
        System.out.println("有新连接");
    }

    /**
     * 验证用户登录
     * @param loginRequestPacket
     * @return
     */
    public boolean valid(LoginRequestPacket loginRequestPacket){
        return true;
    }
}

