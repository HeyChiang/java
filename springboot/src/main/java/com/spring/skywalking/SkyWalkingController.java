package com.spring.skywalking;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 带枚举类型的数据请求处理
 */
@RestController
public class SkyWalkingController {

    @PutMapping("/one")
    Integer home(Integer index) {
        if(index % 2 != 0){
            throw new RuntimeException("异常信息："+index);
        }
        return index;
    }

}
