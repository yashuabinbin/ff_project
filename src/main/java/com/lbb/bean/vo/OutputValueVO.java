package com.lbb.bean.vo;

import com.lbb.model.OutputValueDetailModel;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OutputValueVO {

    private Long outputValueId;

    private Integer outputTime;

    private Date createTime;

    private Integer createUserId;

    private Date lastModifyTime;

    private List<OutputDetailInfoDifVo> difVoList;

    private List<OutputValueDetailVO> outputValueDetailList;

}
