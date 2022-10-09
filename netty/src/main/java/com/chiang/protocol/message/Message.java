package com.chiang.protocol.message;

/**
 * 自定义消息
 */
public interface Message {

     Integer getMessageType();
     Object getContent();
     void setContent(Object content);
     Integer getSequenceId();
}
