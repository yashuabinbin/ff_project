package com.lbb.model;

import java.math.BigDecimal;
import java.util.Date;

public class OutputValueModel extends BaseModel {
    private Long outputValueId;

    private BigDecimal subContractorAmount;

    private BigDecimal migrantWorkAmount;

    private Date outputTime;

    private String remark;

    private Integer createUserId;

    private Date createTime;

    private Date lastModifyTime;

    public Long getOutputValueId() {
        return outputValueId;
    }

    public void setOutputValueId(Long outputValueId) {
        this.outputValueId = outputValueId;
    }

    public BigDecimal getSubContractorAmount() {
        return subContractorAmount;
    }

    public void setSubContractorAmount(BigDecimal subContractorAmount) {
        this.subContractorAmount = subContractorAmount;
    }

    public BigDecimal getMigrantWorkAmount() {
        return migrantWorkAmount;
    }

    public void setMigrantWorkAmount(BigDecimal migrantWorkAmount) {
        this.migrantWorkAmount = migrantWorkAmount;
    }

    public Date getOutputTime() {
        return outputTime;
    }

    public void setOutputTime(Date outputTime) {
        this.outputTime = outputTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}