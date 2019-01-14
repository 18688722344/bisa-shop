package com.bisa.health.shop.admin.dto;

import java.io.Serializable;

/**
 * 用户审核记录表  的Dto
 * @author Administrator
 */
public class SAfterForRecordDto implements Serializable {

    private String uriPic;//用户的头像
    private String username;
    private String orderNo;
    private Integer afterSalesType;// 售后的类型
    private String afterForCause; // 用户售后的原因
    private String createTime;

    public String getUriPic() {
        return uriPic;
    }

    public void setUriPic(String uriPic) {
        this.uriPic = uriPic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getAfterSalesType() {
        return afterSalesType;
    }

    public void setAfterSalesType(Integer afterSalesType) {
        this.afterSalesType = afterSalesType;
    }

    public String getAfterForCause() {
        return afterForCause;
    }

    public void setAfterForCause(String afterForCause) {
        this.afterForCause = afterForCause;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}