package com.bisa.health.shop.admin.model;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name = "s_property_value")
public class PropertyValue implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer propertyId;// 属性表的id

	private Integer valueId;// 属性项 的唯一编号
	
	private String name;// 属性项 名称

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name="property_id")
	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	@Column(name="value_id")
	public Integer getValueId() {
		return valueId;
	}

	public void setValueId(Integer valueId) {
		this.valueId = valueId;
	}

	public PropertyValue() {
		super();
	}

	public PropertyValue(Integer id, Integer propertyId, Integer valueId, String name) {
		super();
		this.id = id;
		this.propertyId = propertyId;
		this.valueId = valueId;
		this.name = name;
	}

	@Override
	public String toString() {
		return "PropertyValue [id=" + id + ", propertyId=" + propertyId + ", valueId=" + valueId + ", name=" + name
				+ "]";
	}

}