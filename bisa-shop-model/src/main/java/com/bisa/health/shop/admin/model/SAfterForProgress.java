package com.bisa.health.shop.admin.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 使用 售后 进度表
 * @author Administrator
 *
 */

@Entity
@Table(name = "s_after_for_progress")
public class SAfterForProgress implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer after_for_record_id;// 用户售后 申请记录表的id
	private Integer user_id;// 用户的id
	private Integer sys_user_id;// 负责处理人的id
	private String progress_description;// 进度描述
	private Integer check_type;// 后台人员审核售后的评价类型 AfterCheckType这个枚举
	private Integer department_type;// 操作人所在的部门 DepartmentTpye这个枚举
	private Date create_time;

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAfter_for_record_id() {
		return after_for_record_id;
	}

	public void setAfter_for_record_id(Integer after_for_record_id) {
		this.after_for_record_id = after_for_record_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getSys_user_id() {
		return sys_user_id;
	}

	public void setSys_user_id(Integer sys_user_id) {
		this.sys_user_id = sys_user_id;
	}

	public String getProgress_description() {
		return progress_description;
	}

	public void setProgress_description(String progress_description) {
		this.progress_description = progress_description;
	}

	public Integer getCheck_type() {
		return check_type;
	}

	public void setCheck_type(Integer check_type) {
		this.check_type = check_type;
	}

	public Integer getDepartment_type() {
		return department_type;
	}

	public void setDepartment_type(Integer department_type) {
		this.department_type = department_type;
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

}