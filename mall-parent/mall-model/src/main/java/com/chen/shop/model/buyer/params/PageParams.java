package com.chen.shop.model.buyer.params;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName PageParams
 * @Description TODO
 * @Author xiaochen
 * @Date 2021/8/13 15:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParams {

    @ApiModelProperty(value = "页号")
    private Integer pageNumber = 1;

    @ApiModelProperty(value = "页面大小")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "排序字段")
    private String sort;

    @ApiModelProperty(value = "排序方式 asc/decs")
    private String order;

    public Boolean checkParams() {
        if (pageNumber == null || pageSize == null) {
            return false;
        }
        return true;
    }
}
