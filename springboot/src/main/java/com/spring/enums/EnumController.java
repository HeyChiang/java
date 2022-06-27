package com.spring.enums;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


/**
 * 带枚举类型的数据请求处理
 */
@RestController
public class EnumController {

    @PutMapping("/put_enum_obj")
    Review home(@RequestBody Review review) {
        Review copyReview = new Review();
        BeanUtils.copyProperties(review,copyReview);
        return copyReview;
    }


    @GetMapping("/get_enum_obj")
    MedicalType getObj(@RequestParam("type") MedicalType medicalType) {
        return medicalType;
    }

    @PostMapping("/post_enum_list")
    List<Review> home(@RequestBody List<Review> reviewList) {
        return reviewList;
    }
}
