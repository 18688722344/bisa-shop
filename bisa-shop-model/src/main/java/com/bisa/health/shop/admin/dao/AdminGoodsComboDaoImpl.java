package com.bisa.health.shop.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.model.GoodsCombo;

@Repository
public class AdminGoodsComboDaoImpl extends BaseDao<GoodsCombo> implements IAdminGoodsComboDao {

    @Override
    public List<GoodsCombo> getGoodsComboGuidByGoodsNumber(String goodsNumber) {
        String sql = "SELECT * FROM s_goods_combo WHERE goods_number = ?";
        return super.listBySql(sql, new Object[]{goodsNumber}, GoodsCombo.class);
    }

    @Override
    public int insertGoodsCombo(GoodsCombo goodsCombo) {
        String sql = "INSERT INTO s_goods_combo( goods_number, combo_guid) VALUES(?,?)";
        return super.addBySql(sql, new Object[]{goodsCombo.getGoodsNumber(), goodsCombo.getComboGuid()});
    }

    @Override
    public int delGoodsCombo(String comboGuid) {
        String sql = "DELETE FROM s_goods_combo WHERE combo_guid= ?";
        return super.deleteBySql(sql, new Object[]{comboGuid});
    }

}
