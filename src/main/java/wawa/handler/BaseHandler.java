package wawa.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import wawa.util.Parser;
import wawa.util.SendClient;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-14
 */
public class BaseHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    public void channelRegistered(ChannelHandlerContext ctx){
        System.out.println("新连接");
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        //1. 获取娃娃发送过来的消息
        ByteBuf byteBuf = (ByteBuf) o;
        // byteBuf.readableBytes() 返回 ByteBuf 的可读长度
        byte[] data =new byte[byteBuf.readableBytes()];
        //2. 将数据写入data
        byteBuf.readBytes(data);
        Parser.parsing(data);
        System.out.println(data);
//        SendClient.sendWawa(channelHandlerContext);
    }
}
