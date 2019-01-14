package com.bisa.health.shop.admin.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 打印订单集合的对象
 * @author Administrator
 */
public class PrintOrderListDto implements Serializable {

    private String name;// 收货人名字
    private String order_no;// 订单编号
    private String start_time;// 下单时间
    private String county_address;// 收货地址

    private Integer count_total;// 商品总数
    private BigDecimal sub_total;// 小计
    private BigDecimal post_price;// 邮费
    private BigDecimal price_total;// 合计金额

    private List<PrintOrderGoodsListDto> page_side;// 打印 订单集合的对象 下面的商品明细对象

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getCounty_address() {
        return county_address;
    }

    public void setCounty_address(String county_address) {
        this.county_address = county_address;
    }

    public Integer getCount_total() {
        return count_total;
    }

    public void setCount_total(Integer count_total) {
        this.count_total = count_total;
    }

    public BigDecimal getSub_total() {
        return sub_total;
    }

    public void setSub_total(BigDecimal sub_total) {
        this.sub_total = sub_total;
    }

    public BigDecimal getPost_price() {
        return post_price;
    }

    public void setPost_price(BigDecimal post_price) {
        this.post_price = post_price;
    }

    public BigDecimal getPrice_total() {
        return price_total;
    }

    public void setPrice_total(BigDecimal price_total) {
        this.price_total = price_total;
    }

    public List<PrintOrderGoodsListDto> getPage_side() {
        return page_side;
    }

    public void setPage_side(List<PrintOrderGoodsListDto> page_side) {
        this.page_side = page_side;
    }

}
