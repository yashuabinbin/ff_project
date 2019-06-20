package com.lbb.enums;

public enum ExceptionCodeEnum {
    LOGIN_FAIL("100000", "登录失败"),

    UNLOGIN("100001", "未登录"),

    CONTRACT_NOT_EXIST("100100", "合同不存在"),

    CONTRACT_NUM_EXISTED("100101", "合同号已存在"),

    CONTRACT_NAME_EXISTED("100102", "合同名称已存在"),

    PAY_INFO_CONTRACT_ID_EXIST("100103", "付款存在记录与该合同有关联，无法删除"),

    INVOICE_INFO_CONTRACT_ID_EXIST("100104", "发票存在记录与该合同有关联，无法删除"),

    SUB_CONTRACTOR_NAME_EXISTED("100200", "分包名已存在"),

    PAY_DETAIL_SUB_CONTRACTOR_ID_EXIST("100201", "付款详情存在记录与该分包有关联，无法删除"),

    INVOICE_DETAIL_SUB_CONTRACTOR_ID_EXIST("100202", "发票详情存在记录与该分包有关联，无法删除"),

    ;

    private String code;

    private String msg;

    ExceptionCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
