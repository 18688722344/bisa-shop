package com.bisa.health.shop.dao;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.model.ServiceCardRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServiceCardRecordDaoImpl extends BaseDao<ServiceCardRecord> implements IServiceCardRecordDao {

    @Override
    public Pager<ServiceCardRecord> getPagerServiceCardRecordByUserGuid(int user_guid) {
        String sql = "SELECT * FROM s_service_card_record WHERE user_guid = ?";
        return super.findBySql(sql, new Object[]{user_guid}, ServiceCardRecord.class, true);
    }

    @Override
    public Pager<ServiceCardRecord> getPagerServiceCardRecordByUserGuid(int user_guid, Integer status) {
        String sql = "SELECT * FROM s_service_card_record WHERE user_guid = ? AND card_status = ?";
        return super.findBySql(sql, new Object[]{user_guid, status}, ServiceCardRecord.class, true);
    }

    @Override
    public List<ServiceCardRecord> getListServiceCardRecordByUserGuid(int user_guid) {
        String sql = "SELECT * FROM s_service_card_record WHERE user_guid = ?";
        return super.listBySql(sql, new Object[]{user_guid}, ServiceCardRecord.class, true);
    }

    @Override
    public List<ServiceCardRecord> getListServiceCardRecordByGuidAndStatus(int user_guid, int card_status) {
        String sql = "SELECT * FROM s_service_card_record WHERE user_guid = ? AND card_status = ?";
        return super.listBySql(sql, new Object[]{user_guid, card_status}, ServiceCardRecord.class, true);
    }

    @Override
    public int addServiceCardRecord(ServiceCardRecord serviceCardRecord) {
        ServiceCardRecord object = super.add(serviceCardRecord);
        return object != null ? 1 : 0;
    }

    @Override
    public int updateServiceCardRecord(ServiceCardRecord serviceCardRecord) {
        super.getSession().evict(serviceCardRecord);
        String sql = "UPDATE s_service_card_record SET active_time=?, card_status=?, account=? WHERE id=?";
        return super.updateBySql(sql, new Object[]{serviceCardRecord.getActiveTime(), serviceCardRecord.getCardStatus(), serviceCardRecord.getAccount(), serviceCardRecord.getId()});
    }

    @Override
    public ServiceCardRecord selectServiceCardRecordByCardNumber(String cardNumber) {
        String sql = "SELECT * FROM s_service_card_record WHERE card_number = ?";
        return super.queryObjectBySql(sql, new Object[]{cardNumber}, ServiceCardRecord.class);
    }

    @Override
    public List<ServiceCardRecord> getLisRecordByUserGuidAndOrderId(int userGuid, Integer orderGoodsId) {
        String sql = "SELECT * FROM s_service_card_record WHERE user_guid = ? AND order_goods_id = ?";
        return super.listBySql(sql, new Object[]{userGuid, orderGoodsId}, ServiceCardRecord.class);
    }

}
