package com.example.singlecell.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ReturnStatusSerializer.class)
public enum ReturnStatus {
    OK("1", "成功"),
    ERR0001("E0001", "传入数据有误"),
    ERR0002("E0002", "数据操作异常");

    private String code;

    private String desc;

    private ReturnStatus(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
