package com.bisa.health.shop.dto;

import java.io.Serializable;

public class ScanCodeReturnDto implements Serializable {
	private static final long serialVersionUID = -963278713475574022L;
	// 状态（SUCCESS:成功，FAILED:失败）
	private String statu;
	// 返回信息
	private String msg;
	// 支付链接（请求成功则有内容，失败则为空）
	private String url;

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ScanCodeReturnDto(String statu, String msg, String url) {
		super();
		this.statu = statu;
		this.msg = msg;
		this.url = url;
	}

}
