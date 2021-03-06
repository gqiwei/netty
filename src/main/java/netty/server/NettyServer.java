package netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import netty.server.handler.ServerHandler;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description 服务端
 * @date 2020-01-08
 */
public class NettyServer {

    private static final int PORT =8080;//绑定端口
    private static final int MAX=4;//尝试绑定次数

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();//接收线程
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();//消息处理线程

        ServerBootstrap  serverBootstrap = new ServerBootstrap();

        serverBootstrap
                .group(bossGroup,workerGroup) //绑定线程
                .channel(NioServerSocketChannel.class) //绑定IO模型
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new ServerHandler());
                    }
                });
        bind(serverBootstrap,PORT,MAX);

    }

    /**
     * 绑定端口,设置监听
     * @param serverBootstrap
     * @param port
     */
    public static void bind(final ServerBootstrap serverBootstrap,final int port,final int max){
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){ //绑定成功
                    System.out.println(port+"端口绑定成功");
                }else if(max==0){
                    System.out.println(port+"端口已被占用,停止绑定。");
                }else{
                    System.out.println(port+"端口已被占用，尝试使用其他端口 ----> "+max);
                    bind(serverBootstrap,port+1,max-1);
                }
            }
        });
    }
}
