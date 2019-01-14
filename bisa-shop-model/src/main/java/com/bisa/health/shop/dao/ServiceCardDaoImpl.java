package com.bisa.health.shop.dao;

import org.springframework.stereotype.Repository;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.model.ServiceCard;

@Repository
public class ServiceCardDaoImpl extends BaseDao<ServiceCard> implements IServiceCardDao {

    @Override
    public int updateServiceCard(ServiceCard serviceCard) {
        String sql = "UPDATE s_service_card SET card_status=? WHERE card_number=?";
        return super.updateBySql(sql, new Object[]{serviceCard.getCardStatus(), serviceCard.getCardNumber()});
    }

    @Override
    public int addServiceCard(ServiceCard serviceCard) {
        String sql = "INSERT INTO s_service_card(card_name, card_number, active_code, goods_number, card_type, service_token, count, card_status)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return super.addBySql(sql, new Object[]{serviceCard.getCardName(), serviceCard.getCardNumber(), serviceCard.getActiveCode(), serviceCard.getGoodsNumber(), serviceCard.getCardType(),
                serviceCard.getServiceToken(), serviceCard.getCount(), serviceCard.getCardStatus()});
    }

    @Override
    public ServiceCard getServiceCardServiceByGoodsNumber(String goodsNumber, int status) {
        String sql = "SELECT * FROM s_service_card WHERE goods_number = ? AND card_status = ? limit 0,1";
        return super.queryObjectBySql(sql, new Object[]{goodsNumber, status}, ServiceCard.class);
    }

}
