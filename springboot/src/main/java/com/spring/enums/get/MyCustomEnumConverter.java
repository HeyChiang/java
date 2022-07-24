package com.spring.enums.get;

import com.spring.enums.MedicalType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;


/**
 * 自定义枚举转化器
 */
public class MyCustomEnumConverter implements Converter<String, MedicalType> {
    @Override

    public MedicalType convert(@NonNull String code) {
        try {
            return MedicalType.convertEnum(code);
        } catch (Exception e) {
            return null;
        }
    }
}