package com.bisa.health.shop.validator;

import org.hibernate.validator.constraints.NotBlank;

import com.bisa.health.shop.contast.HttpMsgConstant;


public class ValidBean {
	
	public interface IphoneLoginGroup{};
	public interface PwdLoginGroup{};
	public interface OtherLoginGroup{};
	public interface IphoneRegGroup{};
	public interface OtherRegGroup{};
	
	@NotBlank(message=HttpMsgConstant.ParamError,groups={IphoneLoginGroup.class,PwdLoginGroup.class,
														OtherLoginGroup.class,IphoneRegGroup.class,
														OtherRegGroup.class})
	private String username;
	
	@NotBlank(message=HttpMsgConstant.ParamError,groups={PwdLoginGroup.class})
	private String password;
	
	@NotBlank(message=HttpMsgConstant.ParamError,groups={IphoneLoginGroup.class,IphoneRegGroup.class,OtherRegGroup.class})
	private String code;
	
	@NotBlank(message=HttpMsgConstant.ParamError,groups={IphoneRegGroup.class,OtherRegGroup.class})
	private String area_code;
	
	@NotBlank(message=HttpMsgConstant.ParamError,groups={OtherRegGroup.class})
	private String iphone;
	
	@NotBlank(message=HttpMsgConstant.ParamError,groups={OtherRegGroup.class})
	private int l_type;
	
	public String getIphone() {
		return iphone;
	}
	public void setIphone(String iphone) {
		this.iphone = iphone;
	}
	public int getL_type() {
		return l_type;
	}
	public void setL_type(int l_type) {
		this.l_type = l_type;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	
	
	
	
	
}
