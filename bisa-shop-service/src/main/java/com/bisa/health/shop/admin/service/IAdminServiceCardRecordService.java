package com.bisa.health.shop.admin.service;

import com.bisa.health.shop.admin.dto.OrderListPageDto;
import com.bisa.health.shop.model.ServiceCard;
import com.bisa.health.shop.model.ServiceCardRecord;

/**
 * 服务卡报告表
 */

public interface IAdminServiceCardRecordService {

    /**
     * 查询激活卡信息
     * @param page
     * @param limit
     * @param searchabout
     * @param incontent
     * @return
     */
    OrderListPageDto<ServiceCardRecord> pageServiceCardRecord(Integer page, Integer limit, String searchabout, String incontent);

    /**
     * 添加激活卡
     * @param serviceCard
     */
    boolean addServiceCard(ServiceCard serviceCard);

    /**
     * 加载所有的服务卡
     * @param page
     * @param limit
     * @param incontent
     * @param searchabout
     * @param val
     * @return
     */
    OrderListPageDto<ServiceCard> loadServiceCardList(Integer page, Integer limit, String incontent, String searchabout, String val);
}
