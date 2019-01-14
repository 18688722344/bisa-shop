package com.bisa.health.shop.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.basic.entity.SystemContext;
import com.bisa.health.shop.admin.dao.IAdminGuestBookRemarkDao;
import com.bisa.health.shop.admin.dto.OrderListPageDto;
import com.bisa.health.shop.admin.model.GuestBookRemark;

@Service
@CacheConfig(cacheNames = "GuestBookRemarkServiceImpl#${redis.exp1}")
public class AdminGuestBookRemarkServiceImpl implements IAdminGuestBookRemarkService {

    @Autowired
    private IAdminGuestBookRemarkDao guestBookRemarkDao;

    @Override
    @CacheEvict(value = "GuestBookRemarkServiceImpl", allEntries = true)
    public void leaveMsgRemark(GuestBookRemark guestBookRemark) {
        guestBookRemarkDao.leaveMsgRemark(guestBookRemark);
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#page.toString() +#limit.toString() +#guestbookId.toString()")
    public OrderListPageDto<GuestBookRemark> getGuestBookRemarkList(Integer page, Integer limit, Integer guestbookId) {
        SystemContext.setPageOffset((page - 1) * limit);
        SystemContext.setPageSize(limit);
        SystemContext.setSort("create_time"); // 默认按订单时间倒序(表的id就是时间的倒序)
        SystemContext.setOrder("desc");
        Pager<GuestBookRemark> pagerOrder = guestBookRemarkDao.getGuestBookRemarkList(guestbookId);

        // 封装layui要的数据结构
        OrderListPageDto<GuestBookRemark> listPageDto = new OrderListPageDto<>();
        listPageDto.setCode(0);
        listPageDto.setMsg("''");
        listPageDto.setCount(pagerOrder.getTotal());
        listPageDto.setData(pagerOrder.getDatas());
        return listPageDto;
    }

}
