package com.lbb.model;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Data
public class PayDetailInfoModel extends BaseModel {

    private Long payDetailId;

    private Long payId;

    private Integer contractId;

    private Integer subContractorId;

    private BigDecimal shareRate;

    private BigDecimal shareAmount;

}