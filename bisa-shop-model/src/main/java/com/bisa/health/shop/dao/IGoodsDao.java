package com.bisa.health.shop.dao;

import com.bisa.health.shop.model.Goods;

import java.util.List;

/**
 * 商品 表
 */

public interface IGoodsDao {

    /**
     * 根据goods编号 查对象
     * @param goods_number
     * @return
     */
    Goods selectGoodsByGoodsNumber(String goods_number);

    /**
     * 修改评论数量
     * @param goods
     * @return
     */
    int updateAppraiseNumber(Goods goods);

    /**
     * 获得指定商品的子级商品列表
     * @param id
     * @return
     */
    List<Goods> listChildrenGoods(Integer id);

}
