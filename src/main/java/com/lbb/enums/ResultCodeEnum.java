package com.lbb.enums;

public enum ResultCodeEnum {
    SUCCESS("200", "成功"),
    ERROR("400", "失败"),
    ;

    private String code;

    private String desc;

    ResultCodeEnum(String code, String desc) {
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
