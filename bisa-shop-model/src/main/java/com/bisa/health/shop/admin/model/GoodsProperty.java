package com.bisa.health.shop.admin.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品标签表
 * @author Administrator
 *
 */
@Entity
@Table(name = "s_goods_property")
public class GoodsProperty implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String goods_number;// 商品的编号
	private String content;// 商品对应的标签的集合

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoods_number() {
		return goods_number;
	}

	public void setGoods_number(String goods_number) {
		this.goods_number = goods_number;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "GoodsProperty [id=" + id + ", goods_number=" + goods_number + ", content=" + content + "]";
	}

}