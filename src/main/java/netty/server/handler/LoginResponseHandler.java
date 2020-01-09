package netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import netty.PacketCodec;
import netty.client.packet.LoginRequestPacket;
import netty.server.packet.LoginResponsePacket;
import netty.util.LoginUtil;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-09
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    private  static int num =0;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        if(valid(loginRequestPacket)){
            num++;
            System.out.println(loginRequestPacket.getUsername()+"登录成功");
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setCode(1);
            loginResponsePacket.setMessage("登陆成功--"+num);
            LoginUtil.markAsLogin(ctx.channel());
            ctx.channel().writeAndFlush(loginResponsePacket);
        }
    }

    public boolean valid(LoginRequestPacket loginRequestPacket){
        return true;
    }
}
