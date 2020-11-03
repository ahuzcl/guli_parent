package com.cunliang.servicebase.exceptionhandler;

import com.cunliang.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.ERROR().message("执行全局异常处理");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.ERROR().message("执行ArithmeticException异常处理");
    }
    @ExceptionHandler(CunliangException.class)
    @ResponseBody
    public R error(CunliangException e){
        e.printStackTrace();
        log.error(e.getMsg());
        return R.ERROR().code(e.getCode()).message(e.getMessage());
    }


}
