package com.bisa.health.shop.admin.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 语言 国际化
 */

public class SelectBean implements Serializable {

    private int key;     //语言的编号
    private String value;//语言的名字
    private boolean flag;//表示这个版本的语言，有没有

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
