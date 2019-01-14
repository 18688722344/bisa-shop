package com.bisa.health.shop.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.model.GoodsImg;

@Repository
public class AdminGoodsImgDaoImpl extends BaseDao<GoodsImg> implements IAdminGoodsImgDao {

    @Override
    public List<GoodsImg> selectMainImgByGoodsNumber(String goods_number ) {
        String sql = "SELECT * FROM s_goods_img WHERE goods_number = ?  order by position asc";
        return super.listBySql(sql, new Object[]{goods_number}, GoodsImg.class);
    }
    @Override
    public List<GoodsImg> selectImgByInternationalization(String goods_number ,int internationalization) {
        String sql = "SELECT * FROM s_goods_img WHERE goods_number = ? and internationalization = ? order by position asc";
        return super.listBySql(sql, new Object[]{goods_number,internationalization}, GoodsImg.class);
    }
    @Override
    public GoodsImg getImgByPosition(String goodsNumber, Integer position, Integer internationalization) {
        String sql = "SELECT * FROM s_goods_img WHERE goods_number = ? AND position = ? AND internationalization = ?";
        return super.queryObjectBySql(sql, new Object[]{goodsNumber, position, internationalization}, GoodsImg.class);
    }

    @Override
    public int addGoodsImg(GoodsImg goodsImg) {
        String sql = "INSERT INTO s_goods_img(goods_number, img_url, position, internationalization) VALUES (?, ?, ?, ?)";
        return super.addBySql(sql, new Object[]{goodsImg.getGoodsNumber(), goodsImg.getImgUrl(), goodsImg.getPosition(), goodsImg.getInternationalization()});

    }

    @Override
    public int updateGoodsImg(String url, String goodsNumber, Integer position, Integer internationalization) {
        String sql = "UPDATE s_goods_img SET img_url = ? WHERE goods_number = ? AND position = ? AND internationalization = ?";
        return super.updateBySql(sql, new Object[]{url, goodsNumber, position, internationalization});
    }

    @Override
    public List<GoodsImg> getIndexImgsByLang(String goodsNumber, Integer lang) {
        String sql = "SELECT * FROM s_goods_img WHERE goods_number = ? and internationalization = ? order by position asc";
        return super.listBySql(sql, new Object[]{goodsNumber, lang}, GoodsImg.class);
    }

    @Override
    public List<GoodsImg> selectGoodsImgs(String goodsNumber, Integer internationalization) {
        String sql = "SELECT * FROM s_goods_img WHERE goods_number = ? and internationalization = ? order by position asc";
        return super.listBySql(sql, new Object[]{goodsNumber, internationalization}, GoodsImg.class);
    }
    @Override
    public int delImg(String goods_number){
        String sql = "DELETE FROM s_goods_img WHERE goods_number= ?";
        return super.deleteBySql(sql, new Object[]{goods_number});
    }
}
