package com.spring.enums;

import lombok.Data;

import java.io.Serializable;

@Data
public class Review implements Serializable {

    private Integer id;
    private Role role;
}
