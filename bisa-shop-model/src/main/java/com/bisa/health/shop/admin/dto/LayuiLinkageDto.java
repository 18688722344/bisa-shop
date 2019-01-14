package com.bisa.health.shop.admin.dto;

import java.io.Serializable;
import java.util.List;

/**
 * layui   联动多选数据格式
 * @author Administrator
 */

public class LayuiLinkageDto implements Serializable {

    private String name;
    private Integer value;
    private List<Object> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<Object> getChildren() {
        return children;
    }

    public void setChildren(List<Object> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "LayuiLinkageDto [name=" + name + ", value=" + value + ", children=" + children + "]";
    }

}
