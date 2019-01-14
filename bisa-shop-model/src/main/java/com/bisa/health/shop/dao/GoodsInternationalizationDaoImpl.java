package com.bisa.health.shop.dao;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.model.GoodsInternationalization;
import org.springframework.stereotype.Repository;

@Repository
public class GoodsInternationalizationDaoImpl extends BaseDao<GoodsInternationalization> implements IGoodsInternationalizationDao {

    @Override
    public GoodsInternationalization selectGoodsInternationalizationById(Integer id, int value) {
        String sql = "SELECT * FROM s_goods_internationalization WHERE goods_id = ? and internationalization = ?";
        return super.queryObjectBySql(sql, new Object[]{id, value}, GoodsInternationalization.class);
    }

    @Override
    public GoodsInternationalization selectGoodsInternationalizationByGoodsNumber(String goodsNumber, int value) {
        String sql = "SELECT * FROM s_goods_internationalization WHERE goods_number = ? and internationalization = ?";
        return super.queryObjectBySql(sql, new Object[]{goodsNumber, value}, GoodsInternationalization.class);
    }
}

