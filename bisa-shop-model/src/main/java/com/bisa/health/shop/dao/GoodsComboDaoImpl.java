package com.bisa.health.shop.dao;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.model.GoodsCombo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoodsComboDaoImpl extends BaseDao<GoodsCombo> implements IGoodsComboDao {

    @Override
    public List<GoodsCombo> getGoodsComboByGoodsNumber(String goodsNumber) {
        String sql = "SELECT * FROM s_goods_combo WHERE goods_number = ?";
        return super.listBySql(sql, new Object[]{goodsNumber}, GoodsCombo.class);
    }

    @Override
    public GoodsCombo getGoodsComboByGuid(String comboGuid) {
        String sql = "SELECT * FROM s_goods_combo WHERE combo_guid = ?";
        return super.queryObjectBySql(sql, new Object[]{comboGuid}, GoodsCombo.class);
    }

}
