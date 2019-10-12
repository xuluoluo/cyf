package com.caiyunfei.cyf.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description: filterQuery
 * date: 2019/8/28 11:46
 * author: 徐家斌
 * version: 1.0
 */
@Data
public class FilterQuery {
    @ApiModelProperty("筛选字段")
    private String type;
    @ApiModelProperty("筛选字段值")
    private String value;
}
