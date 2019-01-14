package com.bisa.health.shop.dao;

import com.bisa.health.shop.model.ServiceCard;

/**
 * 服务卡表
 */
public interface IServiceCardDao {

    /**
     * 更新卡信息-主要修改状态
     * @param serviceCard
     * @return
     */
    public int updateServiceCard(ServiceCard serviceCard);

    /**
     * 批量生成卡
     * @param serviceCard
     * @return
     */
    public int addServiceCard(ServiceCard serviceCard);

    /**
     * 根据商品编号和status获取未使用的第一张卡  用于订单支付完成，关联卡时查询
     * @param goodsNumber
     * @param status
     * @return
     */
    public ServiceCard getServiceCardServiceByGoodsNumber(String goodsNumber, int status);
}
