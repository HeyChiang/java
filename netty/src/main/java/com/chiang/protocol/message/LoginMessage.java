package com.chiang.protocol.message;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录消息发送
 */
@Data
public class LoginMessage implements  Message,Serializable {

    private String userName;
    private String password;
    @Override
    public Integer getMessageType() {
        return MessageType.ONE_TO_ONE.getCode();
    }


    @Override
    public Integer getSequenceId() {
        return 0;
    }
}
