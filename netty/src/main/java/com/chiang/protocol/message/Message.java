package com.chiang.protocol.message;

/**
 * 自定义消息
 */
public interface Message {

     Integer getMessageType();
     String getContent();
     Integer getSequenceId();
}
