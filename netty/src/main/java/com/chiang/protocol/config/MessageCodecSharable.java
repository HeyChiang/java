package com.chiang.protocol.config;

import com.chiang.protocol.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.MessageToMessageCodec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * 消息类的编码和解码，可以共享使用。
 */
@SuppressWarnings("all")
@ChannelHandler.Sharable
public class MessageCodecSharable extends MessageToMessageCodec<ByteBuf, Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message message, List<Object> outList)  {
        ByteBuf outBuf = ctx.alloc().buffer();
        // 很多网络协议都会设置一个随机魔数，来过滤掉不支持这个协议的数据包
        outBuf.writeBytes(new byte[]{3,1,2,4});
        // 协议版本,方便后期的升级
        outBuf.writeByte(1);
        // 序列化的方式，0=JDK，1=JSON
        outBuf.writeByte(0);
        // 消息的类型，登录、私聊、群聊
        outBuf.writeInt(message.getMessageType());
        // 序号，为了双工通信提供异步的功能,int类型占4个字节
        outBuf.writeInt(message.getSequenceId());
//        // 没有意义，为了填充。（说2的n次方看起来更专业）
//        outBuf.writeByte(0xff);

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();ObjectOutputStream oss = new ObjectOutputStream(bos);){
            oss.writeObject(message);
            byte[] content = bos.toByteArray();
            // 消息内容长度，int类型占4个字节
            outBuf.writeInt(content.length);
            // 消息内容
            outBuf.writeBytes(content);
            outList.add(outBuf);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf inBuf, List<Object> list)  {
        int magicNum = inBuf.readInt();
        byte version = inBuf.readByte();
        int serializerType = inBuf.readByte();
        int messageType = inBuf.readInt();
        int sequenceId = inBuf.readInt();
//        inBuf.readByte();

        int length = inBuf.readInt();
        byte[] beanByte =new byte[length];
        inBuf.readBytes(beanByte,0,length);


        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(beanByte));){
            Message message = (Message) ois.readObject();
            list.add(message);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
