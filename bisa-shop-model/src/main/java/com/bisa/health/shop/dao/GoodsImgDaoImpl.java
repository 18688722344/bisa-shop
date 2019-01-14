package com.bisa.health.shop.dao;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.model.GoodsImg;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoodsImgDaoImpl extends BaseDao<GoodsImg> implements IGoodsImgDao {

    @Override
    public List<GoodsImg> selectMainImgByGoodsNumber(String goods_number, int lang) {
        String sql = "SELECT * FROM s_goods_img WHERE goods_number = ? and internationalization = ?";
        return super.listBySql(sql, new Object[]{goods_number, lang}, GoodsImg.class);
    }

    @Override
    public GoodsImg loadMainPicByGoodNumber(String goodsNumber, int value) {
        String sql = "SELECT * FROM s_goods_img WHERE goods_number = ? and position = 1 and internationalization = ?";
        return super.queryObjectBySql(sql, new Object[]{goodsNumber, value}, GoodsImg.class);
    }

    @Override
    public List<GoodsImg> getIndexImg(String goodsNumber, int lang) {
        String sql = "SELECT * FROM s_goods_img WHERE goods_number = ? and internationalization = ?";
        return super.listBySql(sql, new Object[]{goodsNumber, lang}, GoodsImg.class);
    }

}
