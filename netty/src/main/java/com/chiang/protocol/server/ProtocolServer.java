package com.chiang.protocol.server;

import com.chiang.protocol.config.ProtocolFrameDecoder;
import com.chiang.protocol.config.MessageCodecSharable;
import com.chiang.protocol.server.handler.ChatMessageInboundHandler;
import com.chiang.protocol.server.handler.LoginMessageInboundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
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
                ch.pipeline().addLast(new LoginMessageInboundHandler());
                ch.pipeline().addLast(new ChatMessageInboundHandler());
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
