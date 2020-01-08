package netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import netty.PacketCodec;
import netty.client.handler.ClientHandler;
import netty.client.packet.MessageRequestPacket;

import java.util.Scanner;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-08
 */
public class NettyClient {
    private static final String HOST = "10.0.6.98";
    private static final int PORT = 8080;
    private static final int MAX=4;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap =new Bootstrap();

        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new ClientHandler());
                    }
                });

        connect(bootstrap,HOST,PORT,MAX);
    }

    public static void connect (final Bootstrap bootstrap,final String host,final int port,final int max){
        bootstrap.connect(host,port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("连接服务成功");
                    sendMessage(((ChannelFuture) future).channel());
                }else if(max==0){
                    System.out.println("尝试次数剩余0");
                }else{
                    System.out.println("连接失败，重新建立连接 --> "+max);
                    connect(bootstrap,host,port,max-1);
                }
            }
        });
    }

    public static void sendMessage(Channel channel){
        new Thread(new Runnable() {
            @Override
            public void run() {

                while(!Thread.interrupted()){
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();

                    MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
                    messageRequestPacket.setMessage(line);
                    channel.writeAndFlush(PacketCodec.INSTANCE.encode(messageRequestPacket));

                }


            }
        }).start();
    }
}
