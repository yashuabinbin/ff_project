package com.lbb.model;

import java.util.Date;

public class OutputValueModel extends BaseModel {
    private Long outputValueId;

    private Integer outputTime;

    private Date createTime;

    private Integer createUserId;

    private Date lastModifyTime;

    public Long getOutputValueId() {
        return outputValueId;
    }

    public void setOutputValueId(Long outputValueId) {
        this.outputValueId = outputValueId;
    }

    public Integer getOutputTime() {
        return outputTime;
    }

    public void setOutputTime(Integer outputTime) {
        this.outputTime = outputTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}