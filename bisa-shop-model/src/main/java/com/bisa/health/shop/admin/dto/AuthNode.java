package com.bisa.health.shop.admin.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 无限极权限树
 * @author Administrator
 */
public class AuthNode implements Serializable {

    private int id;
    private String name;
    private String value;
    private int parentId;
    private boolean checked;

    private List<AuthNode> list = new ArrayList<AuthNode>();

    public AuthNode() {
        super();
    }

    public AuthNode(int id, String name, String value, int parentId, boolean checked) {
        super();
        this.id = id;
        this.name = name;
        this.value = value;
        this.parentId = parentId;
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<AuthNode> getList() {
        return list;
    }

    public void setList(List<AuthNode> list) {
        this.list = list;
    }

    public int getSize() {
        return list.size();
    }

    // 添加孩子节点
    public void addChild(AuthNode node) {
        list.add(node);
    }

}
