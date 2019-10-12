package com.caiyunfei.cyf.common;

/**
 * @description: 
 * @version: 1.0 
 * @date: 2019/10/10 10:42
 * @author: 徐家斌
 * @params: 
 * @return 
 */


/**
 * 返回码
 */
public class ComCodeMsg {

    /**
     * 成功
     */
    public static final int SUCCESS = 200;
    public static final String SUCCESS_MSG = "成功";




    /**
     * 数据不存在
     */
    public static final Integer NO_DATA = 2000;
    public static final String NO_DATA_MSG = "数据不存在";

    /**
     * 列表数据为空
     */
    public static final Integer EMPTY_LIST =2001;
    public static final String EMPTY_LIST_MSG = "列表数据为空";

    /**
     * 分页数据为空
     */
    public static final Integer EMPTY_PAGE = 2002;
    public static final String EMPTY_PAGE_MSG = "分页数据为空";

    /**
     * 业务层错误
     */
    public static final int BUSINESS_ERROR = 5000;
    public static final String BUSINESS_ERROR_MSG = "业务层错误";

    /**
     * 参数错误
     */
    public static final int PARAM_ERROR = 5001;
    public static final String PARAM_ERROR_MSG = "参数错误";

    /**
     * 缺失token
     */
    public static final String MISSING_TOKEN = "MISSING_TOKEN";

    /**
     * token过期
     */
    public static final String TOKEN_EXPIRED = "TOKEN_EXPIRED";
    public static final String TOKEN_EXPIRED_MSG = "token过期";

    /**
     * token错误
     */
    public static final String TOKEN_ERROR = "TOKEN_ERROR";

    /**
     * 无权访问
     */
    public static final String NO_AUTH = "NO_AUTH_ACCESS";

    /**
     * 密码错误
     */
    public static final String PASS_ERROR = "PASS_ERROR";
    public static final String PASS_ERROR_MSG = "密码错误";

    /**
     * 验证码已发送
     */
    public static final String VERIFY_CODE_SENT = "VERIFY_CODE_SENT";
    public static final String VERIFY_CODE_SENT_MSG = "验证码已发送";

    /**
     * 验证码发送达到上限
     */
    public static final String VERIFY_CODE_SENT_DUPLICATE_SENDING = "VERIFY_CODE_SENT_DUPLICATE_SENDING";
    public static final String VERIFY_CODE_SENT_DUPLICATE_SENDING_MSG = "验证码发送达到上限";

    /**
     * 验证码错误
     */
    public static final String VERIFY_CODE_ERROR = "VERIFY_CODE_ERROR";
    public static final String VERIFY_CODE_ERROR_MSG = "验证码错误";

    /**
     * 验证码过期获或不存在
     */
    public static final String VERIFY_CODE_EXPIRED = "VERIFY_CODE_EXPIRED";
    public static final String VERIFY_CODE_EXPIRED_MSG = "验证码不存在或过期";

    /**
     * 手机号未注册用户
     */
    public static final String MOBILE_NOT_REGISTER = "MOBILE_NOT_REGISTER";
    public static final String MOBILE_NOT_REGISTER_MSG = "该手机号未注册用户";

    /**
     * 手机号已被使用（被注册或绑定）
     */
    public static final String MOBILE_USED = "MOBILE_USED";
    public static final String MOBILE_USED_MSG = "该手机号已被使用";

    /**
     * 账号或密码错误
     */
    public static final String ACCOUNT_PASSWORD_ERROR = "ACCOUNT_PASSWORD_ERROR";
    public static final String ACCOUNT_PASSWORD_ERROR_MSG = "账号或密码错误";


}

