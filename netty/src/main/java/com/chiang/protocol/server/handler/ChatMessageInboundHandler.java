package com.chiang.protocol.server.handler;

import com.chiang.protocol.data.UserData;
import com.chiang.protocol.message.ChatMessage;
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
public class ChatMessageInboundHandler extends SimpleChannelInboundHandler<ChatMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatMessage msg) throws Exception {
        String userName = msg.getTo();
        UserData.USER_CHANNEL.get(userName).writeAndFlush(msg);
    }
}
