package com.chiang.protocol.client;

import com.chiang.protocol.config.ProtocolFrameDecoder;
import com.chiang.protocol.message.ChatMessage;
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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                // 协议帧的处理
                ch.pipeline().addLast(new ProtocolFrameDecoder());
                // 入栈的时候先打logging，然后messageCodec，出栈就反过来了
                ch.pipeline().addLast(loggingHandler);
                ch.pipeline().addLast(messageCodec);
                ch.pipeline().addLast("ClientHandler",new ChannelInboundHandlerAdapter(){

                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        System.out.println("服务器返回："+msg.toString());

                        if(msg instanceof MessageResponse){
                            MessageResponse response = (MessageResponse) msg;
                            if(response.getCode().equals(HttpResponseStatus.OK.code())){
                                System.out.println("登录成功");
                                atomicBoolean.set(true);
                            }else {
                                System.out.println("登录失败");
                                atomicBoolean.set(false);
                            }
                            lock.lock();
                            condition.signal();
                            lock.unlock();
                        }

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

                            // 登录完成以后锁住，等待登录结果的返回
                            lock.lock();
                            try {
                                condition.await();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            lock.unlock();

                            if(atomicBoolean.get()){
                                while (true){
                                    System.out.println("\n------ 可以开始发送消息了  ------");
                                    System.out.println("1.例如消息：send ZhangSan 你好!");
                                    System.out.println("2.例如退出：quit");

                                    String[] cmd = scanner.nextLine().split(" ");
                                    switch (cmd[0]){
                                        case "quit":{
                                            ctx.channel().close();
                                            return;
                                        }
                                        case "send":{
                                            ctx.writeAndFlush(new ChatMessage(cmd[1],username,cmd[2]));
                                            break;
                                        }
                                        default:
                                    }
                                }
                            }else {
                                System.out.println("登录失败，关闭channel！");
                                ctx.channel().close();
                            }

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
