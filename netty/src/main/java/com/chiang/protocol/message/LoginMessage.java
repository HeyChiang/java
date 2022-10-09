package com.chiang.protocol.message;

import java.io.Serializable;

/**
 * 登录消息发送
 */
public class LoginMessage implements  Message,Serializable {

    private Object content = "default";
    @Override
    public Integer getMessageType() {
        return MessageType.ONE_TO_ONE.getCode();
    }

    @Override
    public Object getContent() {
        return content;
    }

    @Override
    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public Integer getSequenceId() {
        return 0;
    }
}
