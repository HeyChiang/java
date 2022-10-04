package com.chiang.protocol.test;

import com.chiang.protocol.message.LoginMessage;
import com.chiang.protocol.message.MessageCodec;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 测试自定义协议的编码和解码
 */
public class TestMessageCodec {
    public static void main(String[] args) {
        EmbeddedChannel channel = new EmbeddedChannel(
                new LoggingHandler(),
                new MessageCodec()
        );

        LoginMessage loginMessage = new LoginMessage();
        System.out.println(channel.writeInbound(loginMessage));
        System.out.println(channel.finish());
    }
}
