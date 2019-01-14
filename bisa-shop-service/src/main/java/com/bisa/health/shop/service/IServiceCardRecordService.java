package com.bisa.health.shop.service;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.model.ServiceCardRecord;

import java.util.List;

/**
 * 服务卡报告表
 */

public interface IServiceCardRecordService {

    /**
     * 根据用户和服务的状态 获取列表
     *
     * @param page
     * @param limit
     * @param user_guid
     * @param status
     * @return
     */
    Pager<ServiceCardRecord> getPagerServiceCardRecordByUserGuid(Integer page, Integer limit, int user_guid, Integer status);

    /**
     * 获得用户所有激活服务的数据
     * @param user_guid
     * @return
     */
    List<ServiceCardRecord> getListServiceCardRecordByUserGuid(int user_guid);

    /**
     * 根据用户id 和使用状态获取的数据
     * @param user_guid
     * @param status
     * @return
     */
    List<ServiceCardRecord> getListServiceCardRecordByGuidAndStatus(int user_guid, int status);

    /**
     * 根据卡号获得使用记录
     * @param cardNumber
     * @return
     */
    ServiceCardRecord selectServiceCardRecordByCardNumber(String cardNumber);

    /**
     * 添加一条报告
     * @param serviceCardRecord
     * @return
     */
    boolean addServiceCardRecord(ServiceCardRecord serviceCardRecord);

    /**
     * 更新报告信息
     * @param serviceCardRecord
     * @return
     */
    boolean updateServiceCardRecord(ServiceCardRecord serviceCardRecord);

    /**
     * 查找记录表
     * 用于判断订单是否已经关联激活卡
     * 一个订单详情同类激活卡数量可能多个，因此返回list
     * @param userGuid
     * @param orderGoodsId
     * @return
     */
    List<ServiceCardRecord> getLisRecordByUserGuidAndOrderId(int userGuid, Integer orderGoodsId);

}

