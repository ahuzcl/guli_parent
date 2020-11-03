package com.cunliang.servicebase.exceptionhandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //生成有参数的构造
@NoArgsConstructor  //生成无参数的构造
public class CunliangException extends RuntimeException {

    private Integer code;
    private String msg;


}
