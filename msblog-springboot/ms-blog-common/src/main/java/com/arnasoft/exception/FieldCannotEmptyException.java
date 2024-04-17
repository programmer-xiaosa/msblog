package com.arnasoft.exception;

/**
 * 字段不能为空异常
 */
public class FieldCannotEmptyException extends BaseException {

    public FieldCannotEmptyException(String msg) {
        super(msg);
    }

}
