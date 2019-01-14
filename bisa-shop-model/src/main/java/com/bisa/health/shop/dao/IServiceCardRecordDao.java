package com.bisa.health.shop.dao;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.model.ServiceCardRecord;

import java.util.List;

/**
 * 服务卡报告表
 */

public interface IServiceCardRecordDao {
    /**
     * 获得用户所有服务卡的分页
     * @param user_guid
     * @return
     */
    Pager<ServiceCardRecord> getPagerServiceCardRecordByUserGuid(int user_guid);

    /**
     * 根据激活卡的状态，获取分页数据
     * @param user_guid
     * @param status
     * @return
     */
    Pager<ServiceCardRecord> getPagerServiceCardRecordByUserGuid(int user_guid, Integer status);

    /**
     * 获得用户所有激活服务的数据
     * @param user_guid
     * @return
     */
    List<ServiceCardRecord> getListServiceCardRecordByUserGuid(int user_guid);

    /**
     * 根据用户id和使用状态获取的数据
     * @param user_guid
     * @param status
     * @return
     */
    List<ServiceCardRecord> getListServiceCardRecordByGuidAndStatus(int user_guid, int status);

    /**
     * 添加激活卡
     * @param serviceCardRecord
     * @return
     */
    int addServiceCardRecord(ServiceCardRecord serviceCardRecord);

    /**
     * 更新激活卡的状态
     * @param serviceCardRecord
     * @return
     */
    int updateServiceCardRecord(ServiceCardRecord serviceCardRecord);

    /**
     * 根据激活卡的号码，获取对象
     * @param cardNumber
     * @return
     */
    ServiceCardRecord selectServiceCardRecordByCardNumber(String cardNumber);

    /**
     * 获取订单下面的所有激活卡
     * @param userGuid
     * @param orderGoodsId
     * @return
     */
    List<ServiceCardRecord> getLisRecordByUserGuidAndOrderId(int userGuid, Integer orderGoodsId);


}
