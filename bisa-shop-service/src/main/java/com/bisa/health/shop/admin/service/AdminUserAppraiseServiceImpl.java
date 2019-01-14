package com.bisa.health.shop.admin.service;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.basic.entity.SystemContext;
import com.bisa.health.shop.admin.dao.IAdminUserAppraiseDao;
import com.bisa.health.shop.admin.dto.OrderListPageDto;
import com.bisa.health.shop.admin.enums.AppraiseStatus;
import com.bisa.health.shop.model.UserAppraise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "UserAppraiseServiceImpl#${redis.exp1}")
public class AdminUserAppraiseServiceImpl implements IAdminUserAppraiseService {

    @Autowired
    private IAdminUserAppraiseDao userAppraiseDao;

    @Override
    @Cacheable(key = "targetClass.name+methodName+#page.toString() +#limit.toString() +#incontent +#searchabout +#val")
    public OrderListPageDto<UserAppraise> selectAllUserAppraise(Integer page, Integer limit, String incontent, String searchabout, String val) {
        // @param searchabout 1 商品编号 2 按年查询 和 3 按年-月查询 4 按年-月-日查询
        // @param val 0 全部 1 失望 2 一般 3 满意 4 喜欢 5 超爱 6 已回复 7 未回复
        SystemContext.setPageOffset((page - 1) * limit);
        SystemContext.setPageSize(limit);
        SystemContext.setSort("appraise_one_time"); // 默认按订单时间倒序(表的id就是时间的倒序)
        SystemContext.setOrder("desc");

        // 高级查询
        Pager<UserAppraise> pagerOrder = null;
        if (searchabout == null && val == null) {// (默认)查询全部
            pagerOrder = userAppraiseDao.getAllUserAppraise();
        } else if ("1".equals(searchabout)) {// 根据 商品编号 查回复的信息
            pagerOrder = userAppraiseDao.selectUserAppraiseListByGoodsNumber(incontent);
        } else if ("2".equals(searchabout)) {// 根据 按年查询 查回复的信息
            pagerOrder = userAppraiseDao.selectUserAppraiseListByYear(incontent);
        } else if ("3".equals(searchabout)) {// 根据 按年-月 查回复的信息
            pagerOrder = userAppraiseDao.selectUserAppraiseListByYearMonth(incontent);
        } else if ("4".equals(searchabout)) {// 根据 按年-月-日 查回复的信息
            pagerOrder = userAppraiseDao.selectUserAppraiseListByYearMonthDay(incontent);
        } else if ("0".equals(val)) {// (默认)查询全部
            pagerOrder = userAppraiseDao.getAllUserAppraise();
        } else if ("1".equals(val)) {// 1 失望
            pagerOrder = userAppraiseDao.getUserAppraise(AppraiseStatus.veryBad.getValue());
        } else if ("2".equals(val)) {// 2 一般
            pagerOrder = userAppraiseDao.getUserAppraise(AppraiseStatus.bad.getValue());
        } else if ("3".equals(val)) {// 3 满意
            pagerOrder = userAppraiseDao.getUserAppraise(AppraiseStatus.general.getValue());
        } else if ("4".equals(val)) {// 4 喜欢
            pagerOrder = userAppraiseDao.getUserAppraise(AppraiseStatus.good.getValue());
        } else if ("5".equals(val)) {// 5 超爱
            pagerOrder = userAppraiseDao.getUserAppraise(AppraiseStatus.veryGood.getValue());
        } else if ("6".equals(val)) {// 6 已回复
            pagerOrder = userAppraiseDao.getUserAppraiseByReplied();
        } else if ("7".equals(val)) {// 7 未回复
            pagerOrder = userAppraiseDao.getUserAppraiseByNoReply();
        }

        // 封装layui要的数据结构
        OrderListPageDto<UserAppraise> listPageDto = new OrderListPageDto<>();
        listPageDto.setCode(0);
        listPageDto.setMsg("''");
        listPageDto.setCount(pagerOrder.getTotal());
        listPageDto.setData(pagerOrder.getDatas());
        return listPageDto;
    }

    @Override
    @CacheEvict(value = "UserAppraiseServiceImpl", allEntries = true)
    public void updateReplyComment(UserAppraise userAppraise) {
        userAppraiseDao.updateReplyComment(userAppraise);
    }

}
