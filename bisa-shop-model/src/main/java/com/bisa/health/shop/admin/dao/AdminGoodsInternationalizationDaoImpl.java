package com.bisa.health.shop.admin.dao;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.model.GoodsInternationalization;
import org.springframework.stereotype.Repository;

@Repository
public class AdminGoodsInternationalizationDaoImpl extends BaseDao<GoodsInternationalization> implements IAdminGoodsInternationalizationDao {

    @Override
    public GoodsInternationalization selectInsternationalzationByInternationalization(String goodsNumber, int internationalization) {
        String sql = "SELECT * FROM s_goods_internationalization WHERE goods_number = ? AND internationalization = ?";
        return super.queryObjectBySql(sql, new Object[]{goodsNumber, internationalization}, GoodsInternationalization.class);
    }

    @Override
    public int insertGoodsInsternationalzation(GoodsInternationalization goodsInter) {
        String sql = "insert into s_goods_internationalization(goods_id, internationalization, goods_number, goods_name, title, description) values (?,?,?,?,?,?)";
        return super.addBySql(sql, new Object[]{goodsInter.getGoods_id(), goodsInter.getInternationalization(), goodsInter.getGoods_number(),
                goodsInter.getGoods_name(), goodsInter.getTitle(), goodsInter.getDescription()});
    }

    @Override
    public int updateInternational(GoodsInternationalization goodsInternationalization) {
        String sql = "update s_goods_internationalization set goods_name=? ,title=?,description=? where goods_number=? and internationalization=?";
        return super.updateBySql(sql, new Object[]{goodsInternationalization.getGoods_name(), goodsInternationalization.getTitle(), goodsInternationalization.getDescription(),
                goodsInternationalization.getGoods_number(), goodsInternationalization.getInternationalization()});
    }

    @Override
    public int updateImgUrl(String url, String goodsNumber, Integer internationalization) {
        String sql = "update s_goods_internationalization set img_url = ? where goods_number = ? and internationalization = ?";
        return super.updateBySql(sql, new Object[]{url, goodsNumber, internationalization});
    }
    @Override
    public int deleteGoodsInternationByGoosNumber(String goods_number) {
        String sql = "DELETE FROM s_goods_internationalization WHERE goods_number= ?";
        return super.deleteBySql(sql, new Object[]{goods_number});
    }
}
