package com.chiang.protocol.message;

import java.io.Serializable;

public class LoginMessage extends Message implements  Serializable {
    @Override
    public Integer getMessageType() {
        return MessageType.ONE_TO_ONE.getCode();
    }

    @Override
    public String getContent() {
        return "Jiang";
    }

    @Override
    public Integer getSequenceId() {
        return 1;
    }
}
