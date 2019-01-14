package com.bisa.health.shop.dto;

import com.bisa.health.shop.model.UserAppraise;

import java.io.Serializable;

/**
 * 用户中心-我的评价页面-封装数据
 * 商品详情页-用户评价-封装数据
 * @author Administrator
 */

public class UserAppraiseDto implements Serializable {

    private UserAppraise userAppraise;
    private String goodsName;
    private String goodsNumber;
    private String imgUrl;

    public UserAppraise getUserAppraise() {
        return userAppraise;
    }

    public void setUserAppraise(UserAppraise userAppraise) {
        this.userAppraise = userAppraise;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public UserAppraiseDto() {
        super();
    }

    public UserAppraiseDto(UserAppraise userAppraise, String goodsName, String goodsNumber, String imgUrl) {
        super();
        this.userAppraise = userAppraise;
        this.goodsName = goodsName;
        this.goodsNumber = goodsNumber;
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "UserAppraiseDto [userAppraise=" + userAppraise + ", goodsName=" + goodsName + ", goodsNumber=" + goodsNumber + ", imgUrl="
                + imgUrl + "]";
    }

}
