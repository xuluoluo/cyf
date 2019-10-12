package com.caiyunfei.cyf.util;

import java.util.UUID;

/**
 * description: UuidUtil
 * date: 2019/7/23 16:11
 * author: 徐家斌
 * version: 1.0
 */
public class UuidUtil {
    public static String getUUID(){
        String  id=UUID.randomUUID().toString();
        id=id.replace("-", "");
        return id;
    }

}
