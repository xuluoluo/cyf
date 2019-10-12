package com.caiyunfei.cyf.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * description: pageQuery
 * date: 2019/8/26 9:12
 * author: 徐家斌
 * version: 1.0
 */
@Data
public class BaseQuery<T> {
    @ApiModelProperty("页码")
    private int pageNo=1;

    @ApiModelProperty("每页显示记录数")
    private int pageSize=10;

    @ApiModelProperty("筛选条件")
    private List<FilterQuery> filters;

    @ApiModelProperty("模糊")
    private String keywords;

    @ApiModelProperty("排序")
    SortQuery sort;

    @ApiModelProperty("业务参数")
    T business;
}
