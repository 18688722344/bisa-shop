package com.bisa.health.shop.utils;

public class JsonResult {

    private String msg;
    private boolean flag;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public JsonResult() {
        super();
    }

    public JsonResult(String msg, boolean flag) {
        super();
        this.msg = msg;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "JsonResult [msg=" + msg + ", flag=" + flag + "]";
    }

}
