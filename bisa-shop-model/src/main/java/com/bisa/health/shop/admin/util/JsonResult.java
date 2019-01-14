package com.bisa.health.shop.admin.util;

/**
 * 返回前端  状态的工具类
 * @author Administrator
 */

public class JsonResult {

    private String msg;// 消息
    private boolean flag;// true 表示成功 false 表示失败
    private Object obj;//返回信息对象


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

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public JsonResult() {
        super();
    }

    public JsonResult(String msg) {
        super();
        this.msg = msg;
    }

    public JsonResult(boolean flag) {
        super();
        this.flag = flag;
    }

    public JsonResult(String msg, boolean flag) {
        super();
        this.msg = msg;
        this.flag = flag;
    }

    public JsonResult(String msg, boolean flag, Object obj) {
        this.msg = msg;
        this.flag = flag;
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "msg='" + msg + '\'' +
                ", flag=" + flag +
                ", obj=" + obj +
                '}';
    }
}
