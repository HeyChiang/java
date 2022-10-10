package com.chiang.protocol.server;

import com.chiang.protocol.config.ProtocolFrameDecoder;
import com.chiang.protocol.data.UserData;
import com.chiang.protocol.message.LoginMessage;
import com.chiang.protocol.config.MessageCodecSharable;
import com.chiang.protocol.message.MessageResponse;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.logging.LoggingHandler;

/**
 * netty服务端，自定义协议
 */
public class ProtocolServer {
    public static void main(String[] args) {
        LoggingHandler loggingHandler = new LoggingHandler();
        MessageCodecSharable messageCodec = new MessageCodecSharable();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.group(boss,worker);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new ProtocolFrameDecoder());
                ch.pipeline().addLast(loggingHandler);
                ch.pipeline().addLast(messageCodec);
                ch.pipeline().addLast(new SimpleChannelInboundHandler<LoginMessage>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, LoginMessage msg) throws Exception {
                        String userName = msg.getUserName();
                        String password = msg.getPassword();
                        boolean check = UserData.check(userName, password);
                        if(check){
                            System.out.println(userName+" 登录成功!");
                            ctx.writeAndFlush(new MessageResponse(HttpResponseStatus.OK.code(), "成功"));
                        }else {
                            System.out.println(userName+" 登录失败!");
                            ctx.writeAndFlush(new MessageResponse(HttpResponseStatus.INTERNAL_SERVER_ERROR.code(), "失败"));
                        }
                    }
                });
            }
        });

        try {
            System.out.println("服务器启动！");
            Channel channel = serverBootstrap.bind(8080).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            boss.shutdownGracefully();
        }

    }
}
