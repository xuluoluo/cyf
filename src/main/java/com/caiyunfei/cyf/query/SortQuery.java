package com.caiyunfei.cyf.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description: SortQuery
 * date: 2019/8/28 11:53
 * author: 徐家斌
 * version: 1.0
 */
@Data
public class SortQuery {
    @ApiModelProperty("排序字段")
    private String type;
    @ApiModelProperty("排序顺序")
    private String direcation;
}
