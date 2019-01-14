package com.bisa.health.shop.admin.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 接收  添加  套餐信息  的dto
 * @author Administrator
 */

public class AddComboDto implements Serializable {

    private String mainGoodsNumber;        // 主商品-编号
    private BigDecimal mainGoodsPrice;    // 主商品-套餐价格
    private Integer mainGoodsCount;    // 主商品-套餐数量

    private String assistantGoodsNumber;    //配套商品-编号
    private BigDecimal assistantGoodsPrice;    //配套商品-套餐价格
    private Integer assistantGoodsCount;    //配套商品-套餐数量

    public String getMainGoodsNumber() {
        return mainGoodsNumber;
    }

    public void setMainGoodsNumber(String mainGoodsNumber) {
        this.mainGoodsNumber = mainGoodsNumber;
    }

    public BigDecimal getMainGoodsPrice() {
        return mainGoodsPrice;
    }

    public void setMainGoodsPrice(BigDecimal mainGoodsPrice) {
        this.mainGoodsPrice = mainGoodsPrice;
    }

    public Integer getMainGoodsCount() {
        return mainGoodsCount;
    }

    public void setMainGoodsCount(Integer mainGoodsCount) {
        this.mainGoodsCount = mainGoodsCount;
    }

    public String getAssistantGoodsNumber() {
        return assistantGoodsNumber;
    }

    public void setAssistantGoodsNumber(String assistantGoodsNumber) {
        this.assistantGoodsNumber = assistantGoodsNumber;
    }

    public BigDecimal getAssistantGoodsPrice() {
        return assistantGoodsPrice;
    }

    public void setAssistantGoodsPrice(BigDecimal assistantGoodsPrice) {
        this.assistantGoodsPrice = assistantGoodsPrice;
    }

    public Integer getAssistantGoodsCount() {
        return assistantGoodsCount;
    }

    public void setAssistantGoodsCount(Integer assistantGoodsCount) {
        this.assistantGoodsCount = assistantGoodsCount;
    }


}
