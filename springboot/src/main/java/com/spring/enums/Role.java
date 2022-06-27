package com.spring.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    PRESCRIBE("1", "处方"), OTC("2", "非处方"), INSTRUMENT("3","医疗器械");

    private final String text;
    private final String name;

    Role(String code, String name) {
        this.text = code;
        this.name = name;
    }

    public String getText() {
        return text;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static Role convertEnum(String text) {
        for (Role r : Role.values()) {
            if (r.getText().equals(text)) {
                return r;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return text;
    }
}
