package com.bisa.health.shop.admin.dao;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.pay.model.Trade;
import com.bisa.health.shop.admin.util.ParameterPackage;

/**
 * 流水 交易表
 * @author Administrator
 *
 */
public interface IAdminTradeDao {

	Pager<Trade> selectAll();// 查询全部

	Pager<Trade> selectTradeByTradeNo(String incontent);//交易号 查询

	Pager<Trade> selectTradeByYear(String incontent);//年查询

	Pager<Trade> selectTradeByYearMonth(String incontent);//年月  查询

	Pager<Trade> selectTradeByYearMonthDay(String incontent);//年月日  查询
	
	boolean updateOrderCheckAfterSale(ParameterPackage parameterPackage);//售后 审核  通过  或者 拒绝

	Pager<Trade> selectTradeRefundList(int value, int value4, int value2, int value3);// 查询全部 退款的对象(财务退款操作的数据)

	Pager<Trade> selectTradeByYearMonthDay0(String incontent, int value, int value4, int value2, int value3);// 交易号 查询(财务退款操作的数据)

	Pager<Trade> selectTradeByYearMonthDay1(String incontent, int value, int value4, int value2, int value3);// 年 查询(财务退款操作的数据)

	Pager<Trade> selectTradeByYearMonthDay2(String incontent, int value, int value4, int value2, int value3);// 年月 查询(财务退款操作的数据)

	Pager<Trade> selectTradeByYearMonthDay3(String incontent, int value, int value4, int value2, int value3);// 年月日 查询(财务退款操作的数据)

	Pager<Trade> selectTradeStatus(int value);// 1 已退款    2 待审核 3 退款中

	Pager<Trade> selectTradeByOrderNo(String incontent);	//根据订单号查询交易流水

}
