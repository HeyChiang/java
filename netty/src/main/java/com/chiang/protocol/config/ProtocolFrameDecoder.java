package com.chiang.protocol.config;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 通讯编码协议，解决半包、黏包的问题。
 *
 * 必须与编码解码类MessageCodec一起使用
 */
public class ProtocolFrameDecoder extends LengthFieldBasedFrameDecoder {

    public ProtocolFrameDecoder(){
        // lengthFieldOffset：偏移量，代表前面魔熟、协议版本等参数
        // lengthFieldLength：描述数据的长度为4个字节
        // initialBytesToScrip：需要剔除掉前面的多少个byte数据
        this(1024,14,4,0,0);
    }

    public ProtocolFrameDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }
}
