package com.bisa.health.shop.admin.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 属性表
 * @author Administrator
 *
 */
@Entity
@Table(name = "s_property")
public class Property implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;// 属性名称
	private Integer classify_id;// 分类表的id

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getClassify_id() {
		return classify_id;
	}

	public void setClassify_id(Integer classify_id) {
		this.classify_id = classify_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Property [id=" + id + ", name=" + name + ", classify_id=" + classify_id + "]";
	}

}