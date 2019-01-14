package com.bisa.health.shop.admin.service;

import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.basic.entity.SystemContext;
import com.bisa.health.pay.model.Trade;
import com.bisa.health.shop.admin.dao.IAdminTradeDao;
import com.bisa.health.shop.admin.dto.OrderListPageDto;
import com.bisa.health.shop.admin.util.ParameterPackage;
import com.bisa.health.shop.enumerate.AfterSalesStatus;

@Service
@CacheConfig(cacheNames = "TradeServiceImpl#${redis.exp1}")
public class AdminTradeServiceImpl implements IAdminTradeService {

    @Autowired
    private IAdminTradeDao tradeDao;

    @Override
    @Cacheable(key = "targetClass.name+methodName+#page.toString() +#limit.toString() +#searchabout +#incontent")
    public OrderListPageDto<Trade> selectTradeInfoAll(Integer page, Integer limit, String searchabout, String incontent) {

        SystemContext.setPageOffset((page - 1) * limit);
        SystemContext.setPageSize(limit);
        SystemContext.setSort("create_time"); // 默认按订单时间倒序
        SystemContext.setOrder("desc");

        // 高级查询
        Pager<Trade> pagerOrder = null;
        if (StringUtils.isEmpty(searchabout)) {// 查询全部
            pagerOrder = tradeDao.selectAll();
        } else if (searchabout.equals("1")) {// 交易号 查询
            pagerOrder = tradeDao.selectTradeByTradeNo(incontent);
        } else if (searchabout.equals("2")) {// 订单号 查询
            pagerOrder = tradeDao.selectTradeByOrderNo(incontent);
        } else if (searchabout.equals("3")) {// 年查询
            pagerOrder = tradeDao.selectTradeByYear(incontent);
        } else if (searchabout.equals("4")) {// 年月 查询
            pagerOrder = tradeDao.selectTradeByYearMonth(incontent);
        } else if (searchabout.equals("5")) {// 年月日 查询
            pagerOrder = tradeDao.selectTradeByYearMonthDay(incontent);
        }

        // 封装layui要的数据结构
        OrderListPageDto<Trade> listPageDto = new OrderListPageDto<>();
        listPageDto.setCode(0);
        listPageDto.setMsg("''");
        listPageDto.setCount(pagerOrder.getTotal());
        listPageDto.setData(pagerOrder.getDatas());
        return listPageDto;
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#page.toString() +#limit.toString() +#searchabout +#incontent")
    public OrderListPageDto<Trade> selectTradeRefundList(Integer page, Integer limit, String searchabout, String incontent, String val) {
        // 高级查询 @param incontent input 输入的内容 和下面的对应
        // @param searchabout option选择 1 交易号 2 年查询 3 年月 查询 4 年月日 查询 val 0 全部 1 已退款 2 待审核
        SystemContext.setPageOffset((page - 1) * limit);
        SystemContext.setPageSize(limit);
        SystemContext.setSort("create_time"); // 默认按订单时间倒序
        SystemContext.setOrder("desc");

        Pager<Trade> pagerOrder = null;
        if (searchabout == null && val == null) {// 查询全部 退款的对象(财务退款操作的数据)
            pagerOrder = tradeDao.selectTradeRefundList(AfterSalesStatus.logistics_success.getValue(),
                    AfterSalesStatus.finance_success.getValue(), AfterSalesStatus.refund_success.getValue(),
                    AfterSalesStatus.refund_failure.getValue());
        } else if ("1".equals(searchabout)) {// 交易号 查询(财务退款操作的数据)
            pagerOrder = tradeDao.selectTradeByYearMonthDay0(incontent, AfterSalesStatus.logistics_success.getValue(),
                    AfterSalesStatus.finance_success.getValue(), AfterSalesStatus.refund_success.getValue(),
                    AfterSalesStatus.refund_failure.getValue());
        } else if ("2".equals(searchabout)) {// 年 查询(财务退款操作的数据)
            pagerOrder = tradeDao.selectTradeByYearMonthDay1(incontent, AfterSalesStatus.logistics_success.getValue(),
                    AfterSalesStatus.finance_success.getValue(), AfterSalesStatus.refund_success.getValue(),
                    AfterSalesStatus.refund_failure.getValue());
        } else if ("3".equals(searchabout)) {// 年月 查询(财务退款操作的数据)
            pagerOrder = tradeDao.selectTradeByYearMonthDay2(incontent, AfterSalesStatus.logistics_success.getValue(),
                    AfterSalesStatus.finance_success.getValue(), AfterSalesStatus.refund_success.getValue(),
                    AfterSalesStatus.refund_failure.getValue());
        } else if ("4".equals(searchabout)) {// 年月日 查询(财务退款操作的数据)
            pagerOrder = tradeDao.selectTradeByYearMonthDay3(incontent, AfterSalesStatus.logistics_success.getValue(),
                    AfterSalesStatus.finance_success.getValue(), AfterSalesStatus.refund_success.getValue(),
                    AfterSalesStatus.refund_failure.getValue());
        } else if (val.equals("0")) {// 查询全部 退款的对象(财务退款操作的数据)
            pagerOrder = tradeDao.selectTradeRefundList(AfterSalesStatus.logistics_success.getValue(),
                    AfterSalesStatus.finance_success.getValue(), AfterSalesStatus.refund_success.getValue(),
                    AfterSalesStatus.refund_failure.getValue());
        } else if (val.equals("1")) {// 1 已退款
            pagerOrder = tradeDao.selectTradeStatus(AfterSalesStatus.refund_success.getValue());
        } else if (val.equals("2")) {// 2 待审核
            pagerOrder = tradeDao.selectTradeStatus(AfterSalesStatus.logistics_success.getValue());
        } else if (val.equals("3")) {// 3 退款中
            pagerOrder = tradeDao.selectTradeStatus(AfterSalesStatus.finance_success.getValue());
        }

        // 封装layui要的数据结构
        OrderListPageDto<Trade> listPageDto = new OrderListPageDto<>();
        listPageDto.setCode(0);
        listPageDto.setMsg("''");
        listPageDto.setCount(pagerOrder.getTotal());
        listPageDto.setData(pagerOrder.getDatas());
        return listPageDto;
    }

    @Override
    @CacheEvict(value = "TradeServiceImpl", allEntries = true)
    public void updateOrderCheckAfterSaleSuccess(String orderNO) {
        tradeDao.updateOrderCheckAfterSale(new ParameterPackage(orderNO, AfterSalesStatus.logistics_success.getValue()));
    }

    @Override
    @CacheEvict(value = "TradeServiceImpl", allEntries = true)
    public void updateOrderFinanceCheckSuccess(String orderNO) {
        tradeDao.updateOrderCheckAfterSale(new ParameterPackage(orderNO, AfterSalesStatus.finance_success.getValue()));
    }

    @Override
    @CacheEvict(value = "TradeServiceImpl", allEntries = true)
    public void updateOrderFinanceCheckFail(String orderNO) {
        tradeDao.updateOrderCheckAfterSale(new ParameterPackage(orderNO, AfterSalesStatus.finance_fail.getValue()));
    }

}
