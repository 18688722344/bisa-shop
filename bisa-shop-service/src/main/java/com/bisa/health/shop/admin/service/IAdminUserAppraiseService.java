package com.bisa.health.shop.admin.service;

import com.bisa.health.shop.admin.dto.OrderListPageDto;
import com.bisa.health.shop.model.UserAppraise;

/**
 * 商品的 评价表
 * @author Administrator
 *
 */
public interface IAdminUserAppraiseService {

	/**
	 * 查询  (评价)  userAppraise 分页集合
	 * @param page   第几页
	 * @param limit  每页有多少数据
	 * @param val 
	 * @param searchabout 
	 * @param incontent 
	 * @return
	 */
	OrderListPageDto<UserAppraise> selectAllUserAppraise(Integer page, Integer limit, String incontent, String searchabout, String val);

	/**
	 * 回复评论
	 * @param userAppraise
	 */
	void updateReplyComment(UserAppraise userAppraise);

}
