package com.lbb.bean.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lbb.bean.dto.SubContractorDTO;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class ContractEditAddReq implements Serializable {

    private static final long serialVersionUID = 1337604926639009038L;

    @JsonProperty("contractId")
    private Integer contractId;

    @JsonProperty("contractNum")
    private String contractNum;

    @JsonProperty("contractName")
    private String contractName;

    @JsonProperty("contractDesc")
    private String contractDesc;

    @JsonProperty("companyName")
    private String companyName;

    @JsonProperty("contractAmount")
    private BigDecimal contractAmount;

    @JsonProperty("taxRate")
    private BigDecimal taxRate;

    @JsonProperty("subContractorList")
    private List<SubContractorDTO> subContractorList;

}
