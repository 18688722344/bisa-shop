package com.bisa.health.shop.validator;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.bisa.health.common.utils.RegexConstants;

public class ValidWebBean {
	
	public interface IPHONE_REG_GROUP{};
	
	public interface SEND_CODE_GROUP{};
	
	public interface BIND_WECHAT_GROUP{};
	
	public interface CHANGE_PWD_SMS_GROUP{};
	
	public interface CHANGE_PWD_EMAIL_GROUP{};
	
	public interface EMAIL_GROUP{};
	
	@NotBlank(groups={IPHONE_REG_GROUP.class, SEND_CODE_GROUP.class, BIND_WECHAT_GROUP.class}, message="手机区号不能为空！")
	private String selectpicker;
	
	@Pattern(groups={IPHONE_REG_GROUP.class, SEND_CODE_GROUP.class, BIND_WECHAT_GROUP.class, CHANGE_PWD_SMS_GROUP.class}, message="手机号格式错误！", regexp="^[1-9]\\d*$")
	private String phone; 
	
	@NotBlank(groups={IPHONE_REG_GROUP.class, CHANGE_PWD_SMS_GROUP.class, CHANGE_PWD_EMAIL_GROUP.class}, message="密码不能为空！")
	private String setpassword; 
	
	@NotBlank(groups={IPHONE_REG_GROUP.class, CHANGE_PWD_SMS_GROUP.class, CHANGE_PWD_EMAIL_GROUP.class}, message="密码不能为空！")
	private String password;
	
	@NotBlank(groups={IPHONE_REG_GROUP.class, CHANGE_PWD_SMS_GROUP.class, CHANGE_PWD_EMAIL_GROUP.class}, message="验证码错误！")
	private String icode;
	
	@NotBlank(groups=BIND_WECHAT_GROUP.class, message="绑定类型有误！")
	private String type;
	
	@Pattern(groups={CHANGE_PWD_EMAIL_GROUP.class, EMAIL_GROUP.class}, message="邮箱格式错误！", regexp=RegexConstants.REGEX_EMAIL)
	private String email;
	
	public String getSelectpicker() {
		return selectpicker;
	}
	public void setSelectpicker(String selectpicker) {
		this.selectpicker = selectpicker;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSetpassword() {
		return setpassword;
	}
	public void setSetpassword(String setpassword) {
		this.setpassword = setpassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIcode() {
		return icode;
	}
	public void setIcode(String icode) {
		this.icode = icode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
