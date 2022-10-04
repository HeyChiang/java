package com.chiang.protocol.message;

import java.io.Serializable;

/**
 * 自定义消息
 */
public abstract class Message implements Serializable {

    abstract Integer getMessageType();
    abstract String getContent();
    abstract Integer getSequenceId();
}
