package com.bisa.health.shop.admin.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 留言 备注表
 * @author Administrator
 *
 */
@Entity
@Table(name = "s_guestbook_remark")
public class GuestBookRemark implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer guestbook_id;// 留言表的id
	
	private String remark_name;// 备注人的名字(后台人员)
	private String remark_content;// 备注的内容
	private Date create_time;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGuestbook_id() {
		return guestbook_id;
	}

	public void setGuestbook_id(Integer guestbook_id) {
		this.guestbook_id = guestbook_id;
	}

	public String getRemark_name() {
		return remark_name;
	}

	public void setRemark_name(String remark_name) {
		this.remark_name = remark_name;
	}

	public String getRemark_content() {
		return remark_content;
	}

	public void setRemark_content(String remark_content) {
		this.remark_content = remark_content;
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
		return "GuestBookRemark [id=" + id + ", guestbook_id=" + guestbook_id + ", remark_name=" + remark_name + ", remark_content="
				+ remark_content + ", create_time=" + create_time + "]";
	}

}