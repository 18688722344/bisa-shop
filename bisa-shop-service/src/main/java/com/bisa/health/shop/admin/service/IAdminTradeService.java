package com.bisa.health.shop.admin.service;

import java.text.ParseException;

import com.bisa.health.pay.model.Trade;
import com.bisa.health.shop.admin.dto.OrderListPageDto;

/**
 * 交易 流水表
 * @author Administrator
 */

public interface IAdminTradeService {

    /**
     * 加载 交易流水 的信息
     * @param page        第几页
     * @param limit       每页有多少数据
     * @param incontent   input  输入的内容    和下面的对应
     * @param searchabout option选择   1 交易号  2 年查询   3   年月  查询   4  年月日  查询
     * @return
     * @throws ParseException
     */
    OrderListPageDto<Trade> selectTradeInfoAll(Integer page, Integer limit, String searchabout, String incontent) throws ParseException;

    /**
     * 加载 退款交易 的信息
     * @param page
     * @param limit
     * @param incontent   input  输入的内容    和下面的对应
     * @param searchabout option选择   1 交易号  2 年查询   3   年月  查询   4  年月日  查询
     * @return
     */
    OrderListPageDto<Trade> selectTradeRefundList(Integer page, Integer limit, String searchabout, String incontent, String val);

    /**
     * 物流 审核 售后  通过
     * @param orderNO
     */
    void updateOrderCheckAfterSaleSuccess(String orderNO);

    /**
     * 财务 审核 售后  通过
     * @param orderNO
     */
    void updateOrderFinanceCheckSuccess(String orderNO);

    /**
     * 财务 审核 售后  拒绝
     * @param orderNO
     */
    void updateOrderFinanceCheckFail(String orderNO);

}
