package com.lbb.bean.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lbb.bean.dto.PayDetailInfoDTO;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ToString
public class PayEditAddReq implements Serializable {

    private static final long serialVersionUID = 5592630279788291630L;

    @JsonProperty("payId")
    private Long payId;

    @JsonProperty("contractId")
    private Integer contractId;

    @JsonProperty("payType")
    private String payType;

    @JsonProperty("payee")
    private String payee;

    @JsonProperty("remark")
    private String remark;

    @JsonProperty("payTime")
    private Date payTime;

    @JsonProperty("payAmount")
    private BigDecimal payAmount;

    @JsonProperty("performanceBoundAmount")
    private BigDecimal performanceBoundAmount;

    @JsonProperty("payDetailInfoList")
    private List<PayDetailInfoDTO> payDetailInfoList;

}
