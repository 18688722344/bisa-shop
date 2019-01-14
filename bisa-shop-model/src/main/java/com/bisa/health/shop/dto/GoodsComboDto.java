package com.bisa.health.shop.dto;

import com.bisa.health.shop.model.Combo;
import com.bisa.health.shop.model.Goods;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用于 购物车显示 的封装DTO  子类
 * 单品和套餐共用
 * @author Administrator
 */
public class GoodsComboDto implements Serializable {

    private static final long serialVersionUID = -3802486884191151562L;

    private Integer id;
    private Integer type; //商品0；套餐1
    private Integer classifyId; //商品分类编号
    private String goodsName; //商品名字
    private String goodsGuid; //套餐编号，可无
    private String goodsNumber; //商品编号
    private BigDecimal goodsPrice; //单价
    private BigDecimal discountPrice; //优惠价格  800
    private Integer count; //套餐内单品数量
    private String imgUrl;

    private Integer needPost; //是否需要邮递
    private Integer goodsStatus;//是否有货

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsGuid() {
        return goodsGuid;
    }

    public void setGoodsGuid(String goodsGuid) {
        this.goodsGuid = goodsGuid;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getNeedPost() {
        return needPost;
    }

    public void setNeedPost(Integer needPost) {
        this.needPost = needPost;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public Integer getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    /**
     * 单品
     * @param goods
     */
    public GoodsComboDto(Goods goods) {
        super();
        this.id = goods.getId();
        this.type = 0;
        this.classifyId = goods.getClassifyId();
        this.goodsName = goods.getGoodsName();
        this.goodsGuid = goods.getGoodsNumber();
        this.goodsPrice = goods.getGoodsPrice();
        this.discountPrice = goods.getGoodsPrice();
        this.count = 1;
        this.imgUrl = goods.getImgUrl();
        this.needPost = goods.getNeedPost();
        this.goodsNumber = goods.getGoodsNumber();
    }

    /**
     * 套餐
     * @param combo
     */
    public GoodsComboDto(Combo combo) {
        super();
        this.id = combo.getId();
        this.type = 1;
        this.classifyId = combo.getClassifyId();
        this.goodsName = combo.getGoodsName();
        this.goodsGuid = combo.getComboGuid();
        this.goodsPrice = combo.getGoodsPrice();
        this.discountPrice = combo.getDiscountPrice();
        this.count = combo.getCount();
        this.imgUrl = combo.getImgUrl();
        this.needPost = combo.getNeedPost();
        this.goodsNumber = combo.getGoodsNumber();
    }

    public GoodsComboDto() {
        super();
    }

    public GoodsComboDto(Integer id, Integer type, Integer classifyId, String goodsName, String goodsGuid,
                         String goodsNumber, BigDecimal goodsPrice, BigDecimal discountPrice, Integer count, String imgUrl,
                         Integer needPost) {
        super();
        this.id = id;
        this.type = type;
        this.classifyId = classifyId;
        this.goodsName = goodsName;
        this.goodsGuid = goodsGuid;
        this.goodsNumber = goodsNumber;
        this.goodsPrice = goodsPrice;
        this.discountPrice = discountPrice;
        this.count = count;
        this.imgUrl = imgUrl;
        this.needPost = needPost;
    }

    @Override
    public String toString() {
        return "GoodsComboDto{" +
                "id=" + id +
                ", type=" + type +
                ", classifyId=" + classifyId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsGuid='" + goodsGuid + '\'' +
                ", goodsNumber='" + goodsNumber + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", discountPrice=" + discountPrice +
                ", count=" + count +
                ", imgUrl='" + imgUrl + '\'' +
                ", needPost=" + needPost +
                ", goodsStatus=" + goodsStatus +
                '}';
    }
}
