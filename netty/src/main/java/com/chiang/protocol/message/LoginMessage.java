package com.chiang.protocol.message;

import com.chiang.protocol.message.enums.MessageType;
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
    public Integer getSerializationType() {
        return 0;
    }
}
