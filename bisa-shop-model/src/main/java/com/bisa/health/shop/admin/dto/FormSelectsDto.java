package com.bisa.health.shop.admin.dto;

import java.io.Serializable;
import java.util.List;

/**
 * layui第三方插件  formSelects要的数据格式
 * @author Administrator
 */

public class FormSelectsDto implements Serializable {

    private Integer code;
    private String msg;
    private List<?> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

}
