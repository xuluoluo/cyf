package com.caiyunfei.cyf.common;

import lombok.Data;

/**
 * 彩云啡异常
 */
@Data
public class ComException extends RuntimeException {
    private Integer code;

    private String error;

    public ComException(Integer code, String error) {
        super(code + ":" + error);
        this.code = code;
        this.error = error;
    }
}
