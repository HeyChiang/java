package com.chiang.protocol.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.logging.LoggingHandler;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * 测试自定义协议的编码和解码
 */
public class TestMessageCodec {
    public static void main(String[] args) throws Exception {
        EmbeddedChannel channel = new EmbeddedChannel(
                new LoggingHandler(),
                new MessageCodec()
        );

        LoginMessage loginMessage = new LoginMessage();
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        new MessageCodec().encode(null,loginMessage,buffer);
        new MessageCodec().decode(null,buffer,new ArrayList<>());
    }
}
