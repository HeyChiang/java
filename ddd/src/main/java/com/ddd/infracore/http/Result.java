package com.ddd.infracore.http;


import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 通用数据返回对象类
 *
 * @author JiangHao
 */
@Data
public class Result<T> {

    private Integer code;
    private String message;
    private T data;


    /**
     * 数据成功返回
     * @param message 提示信息
     * @return 标准成功数据返回对象
     * @param <T> 返回的数据类型
     */
    public static <T> Result<T> success(String message){
        Result<T> result = new Result<>();
        result.setCode(HttpStatus.OK.value());
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.setCode(HttpStatus.OK.value());
        result.setData(data);
        return result;
    }

    /**
     * 请求数据失败返回
     * @param message 提示信息
     * @return 标准异常数据返回
     * @param <T> 数据对象
     */
    public static <T> Result<T> failed(String message){
        Result<T> result = new Result<>();
        result.setCode(HttpStatus.SERVICE_UNAVAILABLE.value());
        result.setMessage(message);
        return result;
    }
}
