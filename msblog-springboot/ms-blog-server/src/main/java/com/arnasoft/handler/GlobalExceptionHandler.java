package com.arnasoft.handler;

import cn.dev33.satoken.exception.SaTokenException;
import com.arnasoft.constant.ErrorCodeConstant;
import com.arnasoft.constant.MessageConstant;
import com.arnasoft.exception.BaseException;
import com.arnasoft.exception.IsTokenException;
import com.arnasoft.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotLoginException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex) {
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage(), ErrorCodeConstant.UNKNOWN_ERROR);
    }

    @ExceptionHandler
    public Result exceptionHandler(IsTokenException ex) {
        return Result.error(ex.getMessage(), ErrorCodeConstant.INVALID_TOKEN);
    }

    /**
     * 处理SQL异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        //Duplicate entry 'zhangsan' for key 'employee.idx_username'
        String message = ex.getMessage();
        if (message.contains("Duplicate entry")) {
            //判断字段是否已存在
            String[] split = message.split(" ");
            String username = split[2];
            String msg = username + MessageConstant.ALREADY_EXISTS;
            return Result.error(msg, ErrorCodeConstant.UNKNOWN_ERROR);
        } else if (message.contains("cannot be null")) {
            //Column 'name' cannot be null
            //判断当前字段是否非空
            String[] split = message.split(" ");
            String fieldName = split[1];
            String msg = fieldName + MessageConstant.FIELD_IS_NOT_NULL;
            return Result.error(msg, ErrorCodeConstant.UNKNOWN_ERROR);
        } else {
            return Result.error(MessageConstant.UNKNOWN_ERROR, ErrorCodeConstant.UNKNOWN_ERROR);
        }
    }

    /**
     * 数据不存在异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(IllegalArgumentException ex) {
        String message = ex.getMessage();
        if (message.contains("Source must not be null")) {
            String msg = MessageConstant.DATA_NOT_FOUND + " " + message;
            return Result.error(msg, ErrorCodeConstant.UNKNOWN_ERROR);
        } else {
            return Result.error(MessageConstant.UNKNOWN_ERROR, ErrorCodeConstant.UNKNOWN_ERROR);
        }
    }

    /**
     * 判断数值类型异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(NumberFormatException ex) {
        String message = ex.getMessage();
        String msg = MessageConstant.PARAMETER_FORMAT_ERROR + " " + message;
        return Result.error(msg, ErrorCodeConstant.UNKNOWN_ERROR);
    }

    @ExceptionHandler
    public Result exceptionHandler(ClassCastException ex) {
        String message = ex.getMessage();
        return Result.error(message, ErrorCodeConstant.UNKNOWN_ERROR);
    }

    @ExceptionHandler
    public Result exceptionHandler(NotPermissionException ex) {
        String message = ex.getMessage();
        return Result.error(MessageConstant.NOT_PERMISSION_ERROR, ErrorCodeConstant.NOT_PERMISSION_ERROR);
    }

    @ExceptionHandler
    public Result exceptionHandler(NotLoginException ex) {
        String message = ex.getMessage();
        return Result.error(message, ErrorCodeConstant.UNKNOWN_ERROR);
    }

    @ExceptionHandler
    public Result exceptionHandler(SaTokenException ex){
        String message = ex.getMessage();
        return Result.error(message, ErrorCodeConstant.UNKNOWN_ERROR);
    }

    @ExceptionHandler
    public Result exceptionHandler(ConnectException ex){
        String message = ex.getMessage();
        return Result.error(message, ErrorCodeConstant.UNKNOWN_ERROR);
    }

    @ExceptionHandler
    public Result exceptionHandler(SQLException ex){
        String message = ex.getMessage();
        return Result.error(message, ErrorCodeConstant.UNKNOWN_ERROR);
    }
}
