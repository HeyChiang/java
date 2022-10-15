package com.chiang.protocol.server.handler;

import com.chiang.protocol.data.UserData;
import com.chiang.protocol.message.LoginMessage;
import com.chiang.protocol.message.MessageResponse;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.Map;

/**
 * 对登录消息用的Handler
 */
public class LoginMessageInboundHandler extends SimpleChannelInboundHandler<LoginMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginMessage msg) throws Exception {
        String userName = msg.getUserName();
        String password = msg.getPassword();
        boolean check = UserData.check(userName, password);
        if (check) {
            UserData.USER_CHANNEL.put(userName, ctx.channel());
            System.out.println(userName + " 登录成功!");
            ctx.writeAndFlush(new MessageResponse(HttpResponseStatus.OK.code(), "成功"));
        } else {
            for (Map.Entry<String, Channel> entry : UserData.USER_CHANNEL.entrySet()) {
                // 如果消息没有对应的解码器，消息就会直接发送不出去，也不会报错（例如：解码器只有Message类型，没有配置String，那么String就会发不出去）
                entry.getValue().writeAndFlush(new MessageResponse(HttpResponseStatus.INTERNAL_SERVER_ERROR.code(), ctx.channel() + "失败了"));
            }

            System.out.println(userName + " 登录失败!");
            ctx.writeAndFlush(new MessageResponse(HttpResponseStatus.INTERNAL_SERVER_ERROR.code(), "失败"));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        System.out.println("异常日志捕捉LoginMessageInboundHandler："+cause.toString());
        if(ctx.channel().isActive()){
            System.out.println("连接异常，关闭连接");
            ctx.close();
        }else {
            System.out.println("异常，发生了什么事？");
        }
    }
}
