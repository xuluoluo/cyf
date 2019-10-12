package com.caiyunfei.cyf.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description: DateResult
 * date: 2019/8/16 13:51
 * author: 徐家斌
 * version: 1.0
 */
@Data
public class ComDataResult<T> {
    /**
     * 编码
     */
    @ApiModelProperty("编码")
    private Boolean result;

    /**
     * 消息
     */
    @ApiModelProperty("消息")
    private String resultMsg;

    @ApiModelProperty("结果")
    private T resultData;

    public ComDataResult() {
    }

    public ComDataResult(Boolean result, String resultMsg, T resultData) {
        this.result = result;
        this.resultMsg = resultMsg;
        this.resultData = resultData;
    }
}
