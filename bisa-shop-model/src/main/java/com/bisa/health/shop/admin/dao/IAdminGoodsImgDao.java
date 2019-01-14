package com.bisa.health.shop.admin.dao;

import java.util.List;

import com.bisa.health.shop.model.GoodsImg;

/**
 * 商品图片类
 */

public interface IAdminGoodsImgDao {

    /**
     * 获得所有图片
     * @param goodsNumber
     * @return
     */
    List<GoodsImg> selectMainImgByGoodsNumber(String goodsNumber);
    /**
     * 获得所有图片
     * @param goodsNumber
     * @param internationalization
     * @return
     */
    List<GoodsImg> selectImgByInternationalization(String goodsNumber,int internationalization);
    /**
     * 添加图片
     * @param goodsImg
     * @return
     */
    int addGoodsImg(GoodsImg goodsImg);

    /**
     * 根据商品的编号和位置去获取图片
     * @param goodsNumber
     * @param position
     * @param internationalization
     * @return
     */
    GoodsImg getImgByPosition(String goodsNumber, Integer position, Integer internationalization);

    /**
     * 更新(图片) 商品的图片表
     * @param url
     * @param goodsNumber
     * @param position
     * @param internationalization
     */
    int updateGoodsImg(String url, String goodsNumber, Integer position, Integer internationalization);

    /**
     * 根据语言，获取首页图片集合
     * @param goodsNumber
     * @param lang
     * @return
     */
    List<GoodsImg> getIndexImgsByLang(String goodsNumber, Integer lang);

    /**
     * 根据商品编号和语言版本，获取商品的图片的集合
     * @param goodsNumber
     * @param internationalization
     * @return
     */
    List<GoodsImg> selectGoodsImgs(String goodsNumber, Integer internationalization);

    /**
     * 删除商品时删除商品的图片
     * @param goods_number
     * @return
     */
    int delImg(String goods_number);
}
