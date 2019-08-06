package com.lbb.model;

import java.math.BigDecimal;
import java.util.Date;

public class OutputValueDetailModel extends BaseModel {
    private Long outputValueDetailId;

    private Integer subContractorId;

    private Long outputValueId;

    private BigDecimal outputAmount;

    private BigDecimal outputCategoryAmount;

    private BigDecimal outputStepAmount;

    private BigDecimal incomeAmount;

    private BigDecimal incomeCategoryAmount;

    private BigDecimal incomeStepAmount;

    private BigDecimal payAmount;

    private Integer createUserId;

    private Date createTime;

    private Date lastModifyTime;

    public Long getOutputValueDetailId() {
        return outputValueDetailId;
    }

    public void setOutputValueDetailId(Long outputValueDetailId) {
        this.outputValueDetailId = outputValueDetailId;
    }

    public Integer getSubContractorId() {
        return subContractorId;
    }

    public void setSubContractorId(Integer subContractorId) {
        this.subContractorId = subContractorId;
    }

    public Long getOutputValueId() {
        return outputValueId;
    }

    public void setOutputValueId(Long outputValueId) {
        this.outputValueId = outputValueId;
    }

    public BigDecimal getOutputAmount() {
        return outputAmount;
    }

    public void setOutputAmount(BigDecimal outputAmount) {
        this.outputAmount = outputAmount;
    }

    public BigDecimal getOutputCategoryAmount() {
        return outputCategoryAmount;
    }

    public void setOutputCategoryAmount(BigDecimal outputCategoryAmount) {
        this.outputCategoryAmount = outputCategoryAmount;
    }

    public BigDecimal getOutputStepAmount() {
        return outputStepAmount;
    }

    public void setOutputStepAmount(BigDecimal outputStepAmount) {
        this.outputStepAmount = outputStepAmount;
    }

    public BigDecimal getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public BigDecimal getIncomeCategoryAmount() {
        return incomeCategoryAmount;
    }

    public void setIncomeCategoryAmount(BigDecimal incomeCategoryAmount) {
        this.incomeCategoryAmount = incomeCategoryAmount;
    }

    public BigDecimal getIncomeStepAmount() {
        return incomeStepAmount;
    }

    public void setIncomeStepAmount(BigDecimal incomeStepAmount) {
        this.incomeStepAmount = incomeStepAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
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