package com.lbb.model;

import lombok.Data;

@Data
public class PayeeModel extends BaseModel {

    private Integer payeeId;

    private String payeeName;

    public PayeeModel(String payeeName) {
        this.payeeName = payeeName;
    }
}