package com.bisa.health.shop.admin.service;

import com.bisa.health.shop.admin.model.SAfterForRecord;

/**
 * 用户 申请记录表
 * @author Administrator
 */

public interface IAdminAfterForRecordService {

    /**
     * 根据order编号  查询用户申请的记录
     * @param orderNo
     * @return
     */
    SAfterForRecord selectApplyForListByOrderNo(String orderNo);

}
