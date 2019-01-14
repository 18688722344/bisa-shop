package com.bisa.health.shop.service;

import com.bisa.health.shop.model.GoodsImg;

import java.util.List;

/**
 * 图片表(商品的和首页)
 */

public interface IGoodsImgService {

    /**
     * 获得所有图片
     * @param goodsNumber 商品的编号
     * @param lang        语言类型
     * @return
     */
    List<GoodsImg> selectMainImgByGoodsNumber(String goodsNumber, String lang);

    /**
     * 获得主图
     * @param goodsNumber
     * @param lang
     * @return
     */
    String loadMainPicByGoodNumber(String goodsNumber, String lang);

    /**
     * 获取首页图片的轮播图，图片集合
     * @param lang
     * @return
     */
    List<GoodsImg> getIndexImg(String lang);
}
