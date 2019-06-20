package com.lbb.enums;

public enum PayTypeEnum {

    SUB_CONTRACTOR("SUB_CONTRACTOR", "分包"),
    MIGRANT_WORKER("MIGRANT_WORKER", "农名工"),
    ;

    private String code;

    private String desc;

    PayTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
