package com.bisa.health.shop.contast;

public class HttpCodeConstant {
	
	public static final int CODE_400=400;//操作异常
	public static final int CODE_401=401;//认证不通过
	
	public static final int CODE_200=200; //操作成功
	public static final int CODE_201=201; //操作成功需要更新
	public static final int CODE_203=203; //已存在
	public static final int CODE_204=204; //密码错误
	public static final int CODE_205=205; //验证码错误
	public static final int CODE_206=206; //需要绑定手机号码
	
	public static final int CODE_230=230; //报告存在
	
	public static final int CODE_223=223; //需要重新登入
	
	public static final int CODE_217=217;//金额不足
	
	
	public static final int CODE_216=216; //没有紧急联系人
	public static final int CODE_218=218; //紧急联系人超出最大
	/**
	 * 信鸽推送状态码
	 */
	public final static int STATUS_400=400;
	public final static int STATUS_200=200;
	
	/**
	 * open
	 */
	public static final int CODE_1000=1000; 
	public static final int CODE_1001=1001;
	public static final int CODE_1002=1002; 
	
	/**
	 * CAS
	 */
	
	public static final int CODE_10000=10000;//成功 
	public static final int CODE_10001=10001;//系统异常
	public static final int CODE_10002=10002; //用户不存在
	public static final int CODE_10003=10003; //验证码不正确
	public static final int CODE_10004=10004; //密码不正确
	public static final int CODE_10005=10005; //绑定手机
}
