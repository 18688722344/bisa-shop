package com.bisa.health.shop.dto;

import java.io.Serializable;

/**
 * 封装订单评价页面的数据显示
 * @author Administrator
 */
public class OrderAppraiseDto implements Serializable {
    //去评价 页面封装数据
    private String goodsName;
    private String goodsNumber;
    private String imgUrl;

    //发表评论 表单提交
    private String appraise; //评论具体内容
    private Integer rateValue; //评分等级

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

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public Integer getRateValue() {
        return rateValue;
    }

    public void setRateValue(Integer rateValue) {
        this.rateValue = rateValue;
    }

    public OrderAppraiseDto() {
        super();
    }

    public OrderAppraiseDto(String goodsName, String goodsNumber, String imgUrl) {
        super();
        this.goodsName = goodsName;
        this.goodsNumber = goodsNumber;
        this.imgUrl = imgUrl;
    }

}
