package com.lbb.bean.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ToString
public class PayInfoVO implements Serializable {

    private static final long serialVersionUID = -5713703842294881470L;

    private Long payId;

    private String payee;

    private Date payTime;

    private BigDecimal payAmount;

    private BigDecimal performanceBoundAmount;

    private String payType;

    private Integer contractId;

    private String contractNum;

    private String contractName;

    private String remark;

    private List<PayDetailInfoVO> payDetailInfoList;
}
