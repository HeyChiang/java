package com.chiang.protocol.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

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
    public Integer getSequenceId() {
        return 1;
    }
}
