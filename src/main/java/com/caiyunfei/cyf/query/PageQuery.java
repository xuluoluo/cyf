package com.caiyunfei.cyf.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description: pageQuery
 * date: 2019/8/26 9:12
 * author: 徐家斌
 * version: 1.0
 */
@Data
public class PageQuery {
    @ApiModelProperty("页码")
    private int pageNo=1;

    @ApiModelProperty("每页显示记录数")
    private int pageSize=10;
}
