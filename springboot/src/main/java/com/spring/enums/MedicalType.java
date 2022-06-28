package com.spring.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MedicalType {

    PRESCRIBE("1", "处方"),
    OTC("2", "非处方"),
    INSTRUMENT("3","医疗器械");

    private final String code;
    private final String name;

    MedicalType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static MedicalType convertEnum(String code) {
        for (MedicalType r : MedicalType.values()) {
            if (r.getCode().equals(code)) {
                return r;
            }
        }

        // 没有找到不抛出异常，直接
        return null;
    }

    @Override
    public String toString() {
        return code;
    }
}
