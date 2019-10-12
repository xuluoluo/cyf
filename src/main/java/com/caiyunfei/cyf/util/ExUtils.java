package com.caiyunfei.cyf.util;


import com.caiyunfei.cyf.common.ComCodeMsg;
import com.caiyunfei.cyf.common.ComException;

import java.util.Collection;

public class ExUtils {

    private ExUtils() {

    }

    /**
     * 用于判断数据为空抛出异常
     *
     * @param data
     * @param error
     */
    public static void notNull(Object data, String error) {
        if (data == null) {
            throw new ComException(ComCodeMsg.BUSINESS_ERROR, error);
        }
    }

    /**
     * 用于数据校验
     *
     * @param data
     * @param dataName
     * @param keyName
     * @param key
     */
    public static void notNull(Object data, String dataName, String keyName, String key) {
        if (data == null) {
            String error = "数据不存在[对象:" + dataName + "," + keyName + ":" + key + "]";
            throw new ComException(ComCodeMsg.BUSINESS_ERROR, error);
        }
    }

    /**
     * 用于主键名为id的数据校验
     *
     * @param data
     * @param dataName
     * @param key
     */
    public static void notNullKeyId(Object data, String dataName, String key) {
        notNull(data, dataName, "id", key);
    }

    /**
     * 用于鉴定集合是否为空
     *
     * @param collection
     * @param error
     * @param <T>
     */
    public static <T> void notEmpty(Collection<T> collection, String error) {
        if (collection == null || collection.isEmpty()) {
            throw new ComException(ComCodeMsg.BUSINESS_ERROR, error);
        }
    }

    /**
     * 用于单个参数的异常
     *
     * @param error
     */
    public static void paramError(String error) {
        throw new ComException(ComCodeMsg.PARAM_ERROR, error);
    }

    /**
     * 用于多个参数的异常
     *
     * @param errors
     */
    public static void multiParamError(String errors) {
        if (!StrUtils.isEmpty(errors)) {
            if (errors.startsWith(",")) {
                errors = errors.replaceFirst(",", "");
            }
            paramError(errors);
        }
    }

    /**
     * 用于抛出业务异常
     *
     * @param error
     */
    public static void business(String error) {
        throw new ComException(ComCodeMsg.BUSINESS_ERROR, error);
    }

    /**
     * 用于抛出多个异常错误
     *
     * @param errors
     */
    public static void multiBusiness(String errors) {
        if (!StrUtils.isEmpty(errors)) {
            if (errors.startsWith(",")) {
                errors = errors.replaceFirst(",", "");
            }
            business(errors);
        }
    }

    /**
     * 用于抛出验证码错误
     */
//    public static void verifyCodeError() {
//        throw new ComException(ComCodeMsg.VERIFY_CODE_ERROR, ComCodeMsg.VERIFY_CODE_ERROR_MSG);
//    }

    /**
     * 用于抛出验证码不存在或者过期
     */
//    public static void verifyCodeExpired(String error) {
//        if (StrUtils.isEmpty(error))
//            throw new ComException(ComCodeMsg.VERIFY_CODE_EXPIRED, ComCodeMsg.VERIFY_CODE_EXPIRED_MSG);
//        throw new ComException(ComCodeMsg.VERIFY_CODE_EXPIRED, error);
//    }

    /**
     * 用于抛出密码错误
     */
//    public static void passError() {
//        throw new ComException(ComCodeMsg.PASS_ERROR, ComCodeMsg.PASS_ERROR_MSG);
//    }

    /**
     * 用于抛出手机号未注册用户
     */
//    public static void mobileNotRegister() {
//        throw new ComException(ComCodeMsg.MOBILE_NOT_REGISTER, ComCodeMsg.MOBILE_NOT_REGISTER_MSG);
//    }

    /**
     * 用于抛出手机号已被使用
     */
//    public static void mobileUsed() {
//        throw new ComException(ComCodeMsg.MOBILE_USED, ComCodeMsg.MOBILE_USED_MSG);
//    }

    /**
     * 用于抛出token过期
     */
//    public static void tokenExpired() {
//        throw new ComException(ComCodeMsg.TOKEN_EXPIRED, ComCodeMsg.TOKEN_EXPIRED_MSG);
//    }
}
