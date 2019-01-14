package com.bisa.health.shop.admin.service;

import com.bisa.health.shop.model.GoodsInternationalization;

/**
 * 商品国家化表
 */
public interface IAdminGoodsInternationalizationService {

    /**
     * 根据商品编号和语言种类，获取对象
     * @param goods_number
     * @param internationalization
     * @return
     */
    GoodsInternationalization selectInsternationalzationByInternationalization(String goods_number, int internationalization);

    /**
     * 添加商品语言
     * @param goodsInter
     */
    void insertGoodsInsternationalzation(GoodsInternationalization goodsInter);

    /**
     * 更新商品国际化语言
     * @param goodsInternationalization
     */
    void updateInternational(GoodsInternationalization goodsInternationalization);

    /**
     * 更新商品国际化语言表图片
     * @param url
     * @param goodsNumber
     * @param internationalization
     */
    void updateImgUrl(String url, String goodsNumber, Integer internationalization);

    /**
     * 根据商品编号删除商品国际化表
     * @param goods_number
     * @return
     */
    boolean deleteGoodsInternationByGoosNumber(String goods_number);

    /**
     * 根据商品编号删除商品
     * @param goods_number
     * @return
     */
    boolean deleteGoodsByGoosNumber(String goods_number);
}
