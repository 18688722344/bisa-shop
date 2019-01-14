package com.bisa.health.shop.admin.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户审核记录表  
 * @author Administrator
 *
 */
@Entity
@Table(name = "s_after_for_record")
public class SAfterForRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer user_id;
	private String order_no;
	private Integer after_sales_type;// 售后的类型 AfterSalesType 这个枚举类
	private String after_for_cause; // 用户售后的原因
	private Date create_time;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public Integer getAfter_sales_type() {
		return after_sales_type;
	}

	public void setAfter_sales_type(Integer after_sales_type) {
		this.after_sales_type = after_sales_type;
	}

	public String getAfter_for_cause() {
		return after_for_cause;
	}

	public void setAfter_for_cause(String after_for_cause) {
		this.after_for_cause = after_for_cause;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SAfterForRecord [id=" + id + ", user_id=" + user_id + ", order_no=" + order_no + ", after_sales_type=" + after_sales_type
				+ ", after_for_cause=" + after_for_cause + ", create_time=" + create_time + "]";
	}

}