package com.bisa.health.shop.admin.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 订单分页的 dto   (layui要的数据格式)
 * @param <T>
 * @author Administrator
 */
public class OrderListPageDto<T> implements Serializable {

    private Integer code; // 成功返回 0
    private String msg; // 成功返回 ""
    private Long count; // 总数
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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "OrderListPageDto [code=" + code + ", msg=" + msg + ", count=" + count + ", data=" + data + "]";
    }

}
