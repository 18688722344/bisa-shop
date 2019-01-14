package com.bisa.health.shop.admin.service;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.basic.entity.SystemContext;
import com.bisa.health.shop.admin.dao.IAdminGuestBookDao;
import com.bisa.health.shop.admin.dto.OrderListPageDto;
import com.bisa.health.shop.admin.enums.HandleStatus;
import com.bisa.health.shop.model.Guestbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "GuestbookServiceImpl#${redis.exp1}")
public class AdminGuestBookServiceImpl implements IAdminGuestBookService {

    @Autowired
    private IAdminGuestBookDao guestBookDao;

    @Override
    @Cacheable(key = "targetClass.name+methodName+#id")
    public Guestbook getLeaveMsg(Integer id) {
        return guestBookDao.getLeaveMsgById(id);
    }

    @Override
    @CacheEvict(value = "GuestBookServiceImpl", allEntries = true)
    public void updateGuestbook(Guestbook guestbook) {
        guestBookDao.updateGuestbook(guestbook);
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#page.toString() +#limit.toString() +#incontent +#searchabout +#val")
    public OrderListPageDto<Guestbook> getLeaveMsgList(Integer page, Integer limit, String incontent, String searchabout, String val) {
        // @param guestbook留言 分页集合 1 姓名 2 电话 3 邮箱
        // @param val 0 全部 1 未处理 2 已处理
        SystemContext.setPageOffset((page - 1) * limit);
        SystemContext.setPageSize(limit);
        SystemContext.setSort("message_time"); // 默认按订单时间倒序(表的id就是时间的倒序)
        SystemContext.setOrder("desc");

        // 高级查询
        Pager<Guestbook> pagerOrder = null;
        if (searchabout == null && val == null) {// (默认)查询全部
            pagerOrder = guestBookDao.getAllLeaveMsg();
        } else if ("1".equals(searchabout)) {// 1 根据 姓名 查询留言
            pagerOrder = guestBookDao.selectLeaveMsgListByName(incontent);
        } else if ("2".equals(searchabout)) {// 2 根据 电话 查询留言
            pagerOrder = guestBookDao.selectLeaveMsgListByPhone(incontent);
        } else if ("3".equals(searchabout)) {// 3 根据 邮箱 查询留言
            pagerOrder = guestBookDao.selectLeaveMsgListByEmail(incontent);
        } else if ("0".equals(val)) {// 查询全部要操作的数据
            pagerOrder = guestBookDao.getAllLeaveMsg();
        } else if ("1".equals(val)) {// 1 待处理
            pagerOrder = guestBookDao.selectLeaveMsgListByHandStatus(HandleStatus.pending.getValue());
        } else if ("2".equals(val)) {// 2 已处理
            pagerOrder = guestBookDao.selectLeaveMsgListByHandStatus(HandleStatus.figured_out.getValue());
        }

        // 封装layui要的数据结构
        OrderListPageDto<Guestbook> listPageDto = new OrderListPageDto<>();
        listPageDto.setCode(0);
        listPageDto.setMsg("''");
        listPageDto.setCount(pagerOrder.getTotal());
        listPageDto.setData(pagerOrder.getDatas());
        return listPageDto;
    }

}
