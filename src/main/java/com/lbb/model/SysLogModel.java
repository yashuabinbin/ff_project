package com.lbb.model;

import lombok.Data;

@Data
public class SysLogModel extends BaseModel {

    private Long logId;

    private String logType;

    private String logContent;

}