package com.caiyunfei.cyf.handler;

import com.caiyunfei.cyf.common.ComCodeMsg;
import com.caiyunfei.cyf.common.ComException;
import com.caiyunfei.cyf.common.ComRT;
import com.caiyunfei.cyf.common.ComResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestControllerAdvice
public class SystemExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(SystemExceptionHandler.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    @ExceptionHandler({ComException.class})
    @ResponseStatus(HttpStatus.OK)
    public ComResult usExceptionHandler(ComException e) {
        if (ComCodeMsg.BUSINESS_ERROR==(e.getCode())) {
            return exception(e);
        }
        log.error(e.getMessage(), e);
        return ComRT.fail(e.getCode(), e.getError());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public ComResult exception(Exception e) {
        String s = sdf.format(new Date());
        String message = "[" + s + "]" + e.getMessage();
        log.error(message, e);
        return ComRT.fail(ComCodeMsg.BUSINESS_ERROR, message);
    }
}
