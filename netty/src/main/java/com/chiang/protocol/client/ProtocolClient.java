package com.chiang.protocol.client;

import com.chiang.protocol.config.ProtocolFrameDecoder;
import com.chiang.protocol.message.LoginMessage;
import com.chiang.protocol.config.MessageCodecSharable;
import com.chiang.protocol.message.MessageResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.logging.LoggingHandler;

import java.util.Scanner;

/**
 * netty客户端，自定义协议
 */
public class ProtocolClient {
    public static void main(String[] args) {
        NioEventLoopGroup group=new NioEventLoopGroup();
        LoggingHandler loggingHandler = new LoggingHandler();
        MessageCodecSharable messageCodec=new MessageCodecSharable();

        Bootstrap bootstrap= new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.group(group);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new ProtocolFrameDecoder());
                ch.pipeline().addLast(loggingHandler);
                ch.pipeline().addLast(messageCodec);
                ch.pipeline().addLast("ClientHandler",new ChannelInboundHandlerAdapter(){

                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        if(msg instanceof MessageResponse){
                            MessageResponse response = (MessageResponse) msg;
                            if(response.getCode().equals(HttpResponseStatus.OK.code())){
                                System.out.println("登录成功");
                            }else {
                                System.out.println("登录失败");
                            }
                        }
                        System.out.println("服务器返回："+msg.toString());
                    }

                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        new Thread(()->{
                            Scanner scanner=new Scanner(System.in);
                            System.out.println("请输入帐号：");
                            String username = scanner.nextLine();
                            System.out.println("请输入密码：");
                            String password = scanner.nextLine();

                            LoginMessage loginMessage = new LoginMessage();
                            loginMessage.setUserName(username);
                            loginMessage.setPassword(password);
                            ctx.writeAndFlush(loginMessage);

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
