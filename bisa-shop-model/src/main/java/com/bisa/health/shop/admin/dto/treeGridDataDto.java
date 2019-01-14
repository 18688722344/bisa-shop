package com.bisa.health.shop.admin.dto;

import java.io.Serializable;
import java.util.List;

/**
 * layui 树形表格 的 dto
 * @param <T>
 * @author Administrator
 */
public class treeGridDataDto<T> implements Serializable {

    private Integer code; // 成功返回 0
    private String msg; // 成功返回 ""
    private Integer count; // 总数
    private boolean is;// true
    private String tip;// 操作成功！
    private List<T> data; // 数据的集合

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public boolean isIs() {
        return is;
    }

    public void setIs(boolean is) {
        this.is = is;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "treeGridDataDto [code=" + code + ", msg=" + msg + ", count=" + count + ", is=" + is + ", tip=" + tip + ", data=" + data
                + "]";
    }

}
