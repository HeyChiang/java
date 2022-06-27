package com.spring.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MedicalType {
    PRESCRIBE("1", "处方"), OTC("2", "非处方"), INSTRUMENT("3","医疗器械");

    private final String text;
    private final String name;

    MedicalType(String code, String name) {
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
    public static MedicalType convertEnum(String text) {
        for (MedicalType r : MedicalType.values()) {
            if (r.getText().equals(text)) {
                return r;
            }
        }

        // 没有找到不抛出异常，直接
        return null;
    }

    @Override
    public String toString() {
        return text;
    }
}
