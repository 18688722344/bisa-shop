package com.bisa.health.shop.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.basic.entity.SystemContext;
import com.bisa.health.shop.admin.dao.IAdminServiceCardDao;
import com.bisa.health.shop.admin.dao.IAdminServiceCardRecordDao;
import com.bisa.health.shop.admin.dto.OrderListPageDto;
import com.bisa.health.shop.admin.enums.AdminCard;
import com.bisa.health.shop.model.ServiceCard;
import com.bisa.health.shop.model.ServiceCardRecord;

@Service
@CacheConfig(cacheNames = "ServiceCardRecordServiceImpl#${redis.exp1}")
public class AdminServiceCardRecordServiceImpl implements IAdminServiceCardRecordService {

    @Autowired
    private IAdminServiceCardRecordDao serviceCardRecordDao;
    @Autowired
    private IAdminServiceCardDao serviceCardDao;

    @Override
    @Cacheable(key = "targetClass.name+methodName+#page.toString()+#limit.toString()+#searchabout+#incontent")
    public OrderListPageDto<ServiceCardRecord> pageServiceCardRecord(Integer page, Integer limit, String searchabout, String incontent) {

        SystemContext.setPageOffset((page - 1) * limit);
        SystemContext.setPageSize(limit);
        SystemContext.setSort("id");
        SystemContext.setOrder("asc");

        Pager<ServiceCardRecord> goodsPager = null;
        if (searchabout == null) {// 默认查询全部
            goodsPager = serviceCardRecordDao.selectAllCard();
        } else if (searchabout.equals("1")) {
            goodsPager = serviceCardRecordDao.selectCardByCardNumber(incontent);// 根据激活卡编号查询
        }
        // 封装layui要的数据结构
        OrderListPageDto<ServiceCardRecord> listPageDto = new OrderListPageDto<>();
        listPageDto.setCode(0);
        listPageDto.setMsg("''");
        listPageDto.setCount(goodsPager.getTotal());
        listPageDto.setData(goodsPager.getDatas());
        return listPageDto;
    }

    @Override
    @CacheEvict(value = "ServiceCardRecordServiceImpl", allEntries = true)
    public boolean addServiceCard(ServiceCard serviceCard) {
        int result = serviceCardDao.addServiceCard(serviceCard);
        return result >= 1 ? true : false;
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#page.toString()+#limit.toString()+#incontent+#searchabout+#val")
    public OrderListPageDto<ServiceCard> loadServiceCardList(Integer page, Integer limit, String incontent, String searchabout, String val) {
        SystemContext.setPageOffset((page - 1) * limit);
        SystemContext.setPageSize(limit);
        SystemContext.setSort("card_number");
        SystemContext.setOrder("desc");

        Pager<ServiceCard> pager = null;
        if (searchabout == null && val == null) {// (默认)查询全部
            pager = serviceCardDao.loadServiceCardList();
        } else if ("1".equals(searchabout)) {// 根据 服务卡编号查询
            pager = serviceCardDao.selecServiceCardByCardNumber(incontent);
        } else if ("0".equals(val)) {// 查询全部
            pager = serviceCardDao.loadServiceCardList();
        } else if ("1".equals(val)) {// 1 未售出
            pager = serviceCardDao.getGoodsByGoodsStatus(AdminCard.unactivated.getValue());
        } else if ("2".equals(val)) {// 2已售出
            pager = serviceCardDao.getGoodsByGoodsStatus(AdminCard.activated.getValue());
        }
        // 封装layui要的数据结构
        OrderListPageDto<ServiceCard> listPageDto = new OrderListPageDto<>();
        listPageDto.setCode(0);
        listPageDto.setMsg("''");
        listPageDto.setCount(pager.getTotal());
        listPageDto.setData(pager.getDatas());
        return listPageDto;
    }

}
