package com.chiang.protocol.message;

import com.chiang.protocol.message.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 所有消息的响应通用类
 */
@Data
@AllArgsConstructor
public class MessageResponse implements Message, Serializable {

    private Integer code;
    private String message;

    @Override
    public Integer getMessageType() {
        return MessageType.ONE_TO_ONE.getCode();
    }

    @Override
    public Integer getSerializationType() {
        return 1;
    }
}
