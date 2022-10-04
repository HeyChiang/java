package com.chiang.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.nio.charset.StandardCharsets;

/**
 * 实现简单的入栈
 */
@SuppressWarnings("all")
public class SimpleNettyHttpServer {
    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.group(boss,worker);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                ch.pipeline().addLast(new HttpServerCodec());

                // 根据类型来选择Handler处理
                ch.pipeline().addLast(new SimpleChannelInboundHandler<HttpRequest>(){

                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
                        System.out.println("接收："+msg);
                        DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(msg.protocolVersion(), HttpResponseStatus.OK);
                        byte[] body = "<H1>Hello Word!</H1>".getBytes();
                        httpResponse.headers().setInt(HttpHeaderNames.CONTENT_LENGTH,body.length);
                        httpResponse.content().writeBytes(body);
                        ctx.writeAndFlush(httpResponse);
                    }
                });
            }
        });
        try{
            ChannelFuture sync = bootstrap.bind(8080).sync();
            sync.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
