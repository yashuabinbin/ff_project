package com.lbb.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class SubContractorVO implements Serializable {

    @ApiModelProperty(value = "分包id")
    private Integer subContractorId;

    @ApiModelProperty(value = "分包名称")
    private String subContractorName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
