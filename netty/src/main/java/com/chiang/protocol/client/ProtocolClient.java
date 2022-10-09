package com.chiang.protocol.client;

import com.chiang.protocol.config.ProtocolFrameDecoder;
import com.chiang.protocol.message.LoginMessage;
import com.chiang.protocol.message.MessageCodec;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;

import java.util.Scanner;

/**
 * netty客户端，自定义协议
 */
public class ProtocolClient {
    public static void main(String[] args) {
        NioEventLoopGroup group=new NioEventLoopGroup();
        LoggingHandler loggingHandler = new LoggingHandler();
        MessageCodec messageCodec=new MessageCodec();

        Bootstrap bootstrap= new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.group(group);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new ProtocolFrameDecoder());
                ch.pipeline().addLast(loggingHandler);
                ch.pipeline().addLast(messageCodec);
                ch.pipeline().addLast("ClientHanler",new ChannelInboundHandlerAdapter(){
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        new Thread(()->{
                            Scanner scanner=new Scanner(System.in);
                            System.out.println("请输入帐号：");
                            String username = scanner.nextLine();
                            System.out.println("请输入密码：");
                            String password = scanner.nextLine();

                            LoginMessage loginMessage = new LoginMessage();
                            loginMessage.setContent(username+password);
                            ctx.writeAndFlush(loginMessage);
                            System.out.println("发送Handler消息成功");
                        },"SystemIn").start();

                    }
                });

            }
        });

        try {
            Channel channel = bootstrap.connect("localhost", 8080).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            group.shutdownGracefully();
        }
    }
}
