package com.bisa.health.shop.admin.dao;

import java.util.List;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.model.Goods;
import com.bisa.health.shop.model.GoodsInternationalization;

/**
 * 商品 表
 * @author Administrator
 */

public interface IAdminGoodsDao {

    /**
     * 根据goods编号 查对象
     * @param goods_number
     * @return
     */
    Goods selectGoodsByGoodsNumber(String goods_number);

    /**
     * 根据分类的id 查询对象
     * @param classifyId
     * @return
     */
    List<Goods> selectGoodsByClassifyId(Integer classifyId);

    /**
     * 添加商品
     * @param goods
     * @return
     */
    int addGoods(Goods goods);

    /**
     * (默认)查询全部
     * @return
     */
    Pager<Goods> selectPagerGoods();

    /**
     * 根据 商品编号 查goods的信息
     * @param incontent
     * @return
     */
    Pager<Goods> selecGoodsByGoodsNumber(String incontent);

    /**
     * 根据 按年查询 查goods的信息
     * @param incontent
     * @return
     */
    Pager<Goods> selectGoodsByYear(String incontent);

    /**
     * 根据 按年-月 查goods的信息
     * @param incontent
     * @return
     */
    Pager<Goods> selectGoodsByYearMonth(String incontent);

    /**
     * 根据 按年-月-日查goods的信息
     * @param incontent
     * @return
     */
    Pager<Goods> selectGoodsByYearMonthDay(String incontent);

    /**
     * 1 销售中 2 下架中
     * @param value
     * @return
     */
    Pager<Goods> getGoodsByGoodsStatus(int value);

    /**
     * 3 不用邮递 4 需要邮递
     * @param value
     * @return
     */
    Pager<Goods> getGoodsByNeedPostTpye(int value);

    /**
     * 更新商品对象
     * @param goods
     * @return
     */
    int updateGoods(Goods goods);


    /**
     * 更新(图片) 商品表的信息
     * @param url
     * @param goodsNumber
     * @return
     */
    int updateGoodsImg(String url, String goodsNumber);

    /**
     * 获取，主商品的对象信息
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
     * @return
     */
    Goods getGoodsById(Integer mainProduct);

    /**
     * 根据商品编号删除商品
     * @param goods_number
     * @return
     */
   int deleteGoodsByGoosNumber(String goods_number);
}
