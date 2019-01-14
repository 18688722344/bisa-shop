package com.bisa.health.shop.service;


import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.model.UserAppraise;

/**
 * 用户评论表
 */

public interface IUserAppraiseService {

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
     * 添加用户的评论
     * @param userAppraise
     * @return
     */
    boolean addUserAppraise(UserAppraise userAppraise);

    /**
     * 更新用户的评论
     * @param userAppraise
     * @return
     */
    boolean updateUserAppraise(UserAppraise userAppraise);

    /**
     * 根据评论表的id，获取评论对象
     * @param id 评论表的id
     * @return
     */
    UserAppraise getAppraiseById(Integer id);

}
