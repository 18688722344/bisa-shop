package com.bisa.health.shop.admin.service;

import com.bisa.health.shop.admin.dto.OrderListPageDto;
import com.bisa.health.shop.model.Goods;

import java.util.List;

/**
 * 商品表
 */

public interface IAdminGoodsService {

    /**
     * 根据商品的编号   获取商品的对象
     * @param goods_number_id
     * @return
     */
    Goods selectGoodsByGoodsNumber(String goods_number_id);

    /**
     * 根据classifyId 获取goods列表
     * @param classifyId
     * @return
     */
    List<Goods> selectGoodsByClassifyId(Integer classifyId);

    /**
     * 添加商品
     * @param goods
     */
    boolean addGoods(Goods goods);

    /**
     * 查询  (商品对象)  goods 分页集合
     * @param page
     * @param limit
     * @param incontent
     * @param searchabout
     * @param val
     * @return
     */
    OrderListPageDto<Goods> selectAllGoods(Integer page, Integer limit, String incontent, String searchabout, String val);

    /**
     * 更新商品对象
     * @param goods
     */
    void updateGoods(Goods goods);

    /**
     * 更新(图片) 商品表的信息
     * @param url
     * @param goodsNumber
     */
    void updateGoodsImg(String url, String goodsNumber);

    /**
     * 异步加载  主商品的对象信息
     * @return
     */
    List<Goods> loadMainGoodsObject();

    /**
     * 获取所有的商品对象
     * @return
     */
    List<Goods> loadGoodsList();

    /**
     * 根据goods表的id去查询对象
     * @param mainProduct
     */
    Goods getGoodsById(Integer mainProduct);


}
