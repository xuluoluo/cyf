package com.caiyunfei.cyf.enums.common;

/**
 * description: RedisTyoeEnum
 * date: 2019/9/29 13:21
 * author: 徐家斌
 * version: 1.0
 */
public enum RedisTyoeEnum {
    USER("用户类型"),
    SMSCODE("验证码类型");
    private String value;

    RedisTyoeEnum(String value) {
        this.value = value;
    }
}
