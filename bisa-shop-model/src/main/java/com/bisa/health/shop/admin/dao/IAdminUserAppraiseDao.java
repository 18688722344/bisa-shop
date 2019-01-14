package com.bisa.health.shop.admin.dao;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.model.UserAppraise;
/**
 * 评论表
 * @author Administrator
 *
 */
public interface IAdminUserAppraiseDao {

	/**
	 * 回复评论
	 * @param userAppraise
	 * @return
	 */
	int updateReplyComment(UserAppraise userAppraise);

	/**
	 * 获取所有的评论信息，根据评论的时间倒序排列的
	 * @return
	 */
	Pager<UserAppraise> getAllUserAppraise();

	/**
	 * 根据 商品编号 查回复的信息
	 * @param incontent
	 * @return
	 */
	Pager<UserAppraise> selectUserAppraiseListByGoodsNumber(String incontent);

	/**
	 * 根据 按年查询 查回复的信息
	 * @param incontent
	 * @return
	 */
	Pager<UserAppraise> selectUserAppraiseListByYear(String incontent);

	/**
	 * 根据 按年-月查询 查回复的信息
	 * @param incontent
	 * @return
	 */
	Pager<UserAppraise> selectUserAppraiseListByYearMonth(String incontent);

	/**
	 * 根据 按年-月-日查询   回复的信息
	 * @param incontent
	 * @return
	 */
	Pager<UserAppraise> selectUserAppraiseListByYearMonthDay(String incontent);

	/**
	 * 1 失望  2 一般  3 满意  4 喜欢  5 超爱
	 * @param value
	 * @return
	 */
	Pager<UserAppraise> getUserAppraise(int value);

	/**
	 * 6  已回复
	 * @return
	 */
	Pager<UserAppraise> getUserAppraiseByReplied();

	/**
	 * 7 未回复
	 * @return
	 */
	Pager<UserAppraise> getUserAppraiseByNoReply();

}
