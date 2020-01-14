package netty.server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import netty.util.SessionUtil;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-09
 */
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {
    public static final AuthHandler INSTANCE = new AuthHandler();
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object o) throws Exception {
        if(SessionUtil.hasLogin(ctx.channel())){
            ctx.pipeline().remove(this);
            super.channelRead(ctx,o);
        }else{
            System.out.println("未登录");
            ctx.channel().close();
        }

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if(SessionUtil.hasLogin(ctx.channel())){
            System.out.println("已登录，移除AuthHandler");
        }else{
            System.out.println("未登录，强制断开连接");
        }
    }

}
