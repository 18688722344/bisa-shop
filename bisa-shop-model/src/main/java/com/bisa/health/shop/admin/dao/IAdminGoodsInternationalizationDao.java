package com.bisa.health.shop.admin.dao;

import com.bisa.health.shop.model.GoodsInternationalization;

/**
 * 商品国际化表
 */
public interface IAdminGoodsInternationalizationDao {

    /**
     * 根据商品的编号和语言，获取商品国际化语言对象
     * @param goodsNumber
     * @param internationalization
     * @return
     */
    GoodsInternationalization selectInsternationalzationByInternationalization(String goodsNumber, int internationalization);

    /**
     * 新增商品 语言版本
     * @param goodsInter
     */
    int insertGoodsInsternationalzation(GoodsInternationalization goodsInter);


    /**
     * 更新商品国际化语言
     * @param goodsInternationalization
     * @return
     */
    int updateInternational(GoodsInternationalization goodsInternationalization);

    /**
     * 更新商品国际化语言表图片
     * @param url
     * @param goodsNumber
     * @param internationalization
     */
    int updateImgUrl(String url, String goodsNumber, Integer internationalization);

    /**
     * 根据商品编号删除商品国际化表
     * @param goods_number
     * @return
     */
    int deleteGoodsInternationByGoosNumber(String goods_number);
}
