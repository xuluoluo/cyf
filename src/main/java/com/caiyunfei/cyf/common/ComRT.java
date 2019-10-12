package com.caiyunfei.cyf.common;

import java.util.List;

/**
 * 返回工具类
 */
@SuppressWarnings("unchecked")
public class ComRT {

    /**
     * 用于结果执行成功的返回，不关心数据
     *
     * @return
     */
    public static ComResult success() {
        return new ComResult(ComCodeMsg.SUCCESS, ComCodeMsg.SUCCESS_MSG, null);
    }

    /**
     * 用于结果执行成功的返回，关心数据，比如新增返回id等接口返回
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ComResult<T> success(T data) {
        return new ComResult<>(ComCodeMsg.SUCCESS, ComCodeMsg.SUCCESS_MSG, data);
    }

    /**
     * 此方法用于统一拦截器中，不建议单独使用
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ComResult<T> fail(Integer code, String message) {
        return new ComResult<>(code, message, null);
    }

    /**
     * 用于查询单个数据的返回
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ComResult<T> data(T data) {
        if (data == null) {
            return fail(ComCodeMsg.NO_DATA, ComCodeMsg.NO_DATA_MSG);
        }
        return success(data);
    }

    /**
     * 用于查询数据列表的返回
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> ComResult<List<T>> list(List<T> list) {
        if (list == null || list.size() == 0) {
            return fail(ComCodeMsg.EMPTY_LIST, ComCodeMsg.EMPTY_LIST_MSG);
        }
        return success(list);
    }



}
