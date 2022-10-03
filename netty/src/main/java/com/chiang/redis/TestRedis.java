package com.chiang.redis;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TestRedis {

    // 回车符是13，换行符是10
    static final byte[] CHARACTER ={13,10};

    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(worker);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new LoggingHandler());
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            // 在连接被激活的时候，发送消息给redis
                            ByteBuf buffer = ctx.alloc().buffer();

                            // *3代表 命令分三段
                            buffer.writeBytes("*3".getBytes());
                            buffer.writeBytes(CHARACTER);
                            writeString(buffer,"set");
                            writeString(buffer,"name");
                            writeString(buffer,"haha");

//                            封装到方法中
//                            buffer.writeBytes("$3".getBytes());
//                            buffer.writeBytes(CHARACTER);
//                            buffer.writeBytes("set".getBytes());
//                            buffer.writeBytes(CHARACTER);
//                            buffer.writeBytes("$4".getBytes());
//                            buffer.writeBytes(CHARACTER);
//                            buffer.writeBytes("name".getBytes());
//                            buffer.writeBytes(CHARACTER);
//                            buffer.writeBytes("$5".getBytes());
//                            buffer.writeBytes(CHARACTER);
//                            buffer.writeBytes("jiang".getBytes());
//                            buffer.writeBytes(CHARACTER);

                            ctx.writeAndFlush(buffer);
                        }

                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            ByteBuf byteBuf = (ByteBuf) msg;
                            System.out.println(byteBuf.toString(Charset.defaultCharset()));
                        }
                    });
                }
            });
            ChannelFuture channelFuture = bootstrap.connect("localhost", 6379).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            worker.shutdownGracefully();
        }
    }

    /**
     * 写如redis需要的协议格式
     * @param byteBuf 缓存区
     * @param msg 字符串信息
     */
    private static void writeString(ByteBuf byteBuf,String msg){
        String len = "$"+msg.length();
        byteBuf.writeBytes(len.getBytes());
        byteBuf.writeBytes(CHARACTER);
        byteBuf.writeBytes(msg.getBytes());
        byteBuf.writeBytes(CHARACTER);
    }
}
