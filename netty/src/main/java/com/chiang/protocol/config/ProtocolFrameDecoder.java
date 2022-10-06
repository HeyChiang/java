package com.chiang.protocol.config;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 通讯编码协议，解决半包、黏包的问题。
 *
 * 必须与编码解码类MessageCodec一起使用
 */
public class ProtocolFrameDecoder extends LengthFieldBasedFrameDecoder {

    public ProtocolFrameDecoder(){
        this(1024,14,4,0,0);
    }

    public ProtocolFrameDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }
}
