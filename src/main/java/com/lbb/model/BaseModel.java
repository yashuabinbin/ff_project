package com.lbb.model;

import lombok.Data;

import java.util.Date;

@Data
public class BaseModel {

    private Date createTime;

    private Date lastModifyTime;

    private Integer createUserId;

}
