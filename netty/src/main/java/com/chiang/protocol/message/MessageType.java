package com.chiang.protocol.message;

import lombok.Getter;

@Getter
public enum MessageType {
    ONE_TO_ONE(0),
    ONE_TO_MULTIPLE(1);


    private final Integer code;
    MessageType(int code) {
        this.code = code;
    }
}
