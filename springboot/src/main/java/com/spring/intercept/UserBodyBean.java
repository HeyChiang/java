package com.spring.intercept;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chiang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public  class UserBodyBean {
    String name = "张三";
    String address = "上海";
}
