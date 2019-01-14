package com.bisa.health.shop.dao;

import com.bisa.health.shop.model.GoodsImg;

import java.util.List;

/**
 * 商品图片表
 */
public interface IGoodsImgDao {

    /**
     * 获得所有图片
     * @param goodsNumber
     * @param lang
     * @return
     */
    List<GoodsImg> selectMainImgByGoodsNumber(String goodsNumber, int lang);

    /**
     * 获得主图
     * @param goodsNumber
     * @param value
     * @return
     */
    GoodsImg loadMainPicByGoodNumber(String goodsNumber, int value);

    /**
     * 获取首页图片的轮播图，图片集合
     * @param goodsNumber
     * @param lang
     * @return
     */
    List<GoodsImg> getIndexImg(String goodsNumber, int lang);
}
