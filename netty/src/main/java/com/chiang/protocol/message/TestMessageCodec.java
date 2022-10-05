package com.chiang.protocol.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * 测试自定义协议的编码和解码
 */
public class TestMessageCodec {
    public static void main(String[] args) throws Exception {
        EmbeddedChannel channel = new EmbeddedChannel(
                // lengthFieldOffset：偏移量，代表前面魔熟、协议版本等参数
                // lengthFieldLength：描述数据的长度为4个字节
                // initialBytesToScrip：需要剔除掉前面的多少个byte数据
                new LengthFieldBasedFrameDecoder(1024,14,4,0,0),
                new LoggingHandler(),
                new MessageCodec()
        );


        LoginMessage loginMessage = new LoginMessage();
        channel.writeOutbound(loginMessage);

        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        new MessageCodec().encode(null,loginMessage,buffer);
        channel.writeInbound(buffer);
    }
}
