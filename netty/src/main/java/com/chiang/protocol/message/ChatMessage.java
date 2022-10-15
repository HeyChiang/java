package com.chiang.protocol.message;

import com.chiang.protocol.message.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 登录消息发送
 */
@Data
@AllArgsConstructor
public class ChatMessage implements  Message,Serializable {

    private String to;
    private String from;
    private String content;
    @Override
    public Integer getMessageType() {
        return MessageType.ONE_TO_ONE.getCode();
    }


    @Override
    public Integer getSerializationType() {
        return 0;
    }
}
