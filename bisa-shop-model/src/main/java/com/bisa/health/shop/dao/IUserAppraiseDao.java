package com.bisa.health.shop.dao;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.model.UserAppraise;

/**
 * 用户评论表
 */
public interface IUserAppraiseDao {

    /**
     * 获得某个商品所有用户评价
     * 用于商品详情页
     * @param goodsNumber
     * @return
     */
    Pager<UserAppraise> getPagerAppraiseByGoodsNumber(String goodsNumber);

    /**
     * 用于获得某个用户所有商品的评价
     * 用于订单中心-我的评价
     * @param userGuid
     * @return
     */
    Pager<UserAppraise> getPagerAppraiseByUserGuid(Integer userGuid);

    /**
     * 添加用户 评价
     * @param userAppraise
     * @return
     */
    int addUserAppraise(UserAppraise userAppraise);

    /**
     * 更新用户评价
     * @param userAppraise
     * @return
     */
    int updateUserAppraise(UserAppraise userAppraise);

    /**
     * 获取用户评价
     * @param id
     * @return
     */
    UserAppraise getAppraiseById(Integer id);
}
