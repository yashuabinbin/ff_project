package com.lbb.bean.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class PayIdReq implements Serializable {

    private static final long serialVersionUID = 3969393907730095120L;

    @JsonProperty("payId")
    private Long payId;

}
