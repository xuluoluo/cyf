package com.caiyunfei.cyf.util;

import org.springframework.util.StringUtils;

import java.util.Random;

public class StrUtils extends StringUtils {
    public static boolean eq(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        } else if (str1 != null && str2 != null) {
            return str1.equals(str2);
        }
        return false;
    }

    public static boolean neq(String str1, String str2) {
        return !eq(str1, str2);
    }

    public static boolean notEmpty(String s) {
        return !isEmpty(s);
    }

    public static String genValidSN6(){
        return genValidSN(6);
    }
    public static String genValidSN(int len){
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<len;i++){
            sb.append(new Random().nextInt(9));
        }
        return sb.toString();
    }
}

