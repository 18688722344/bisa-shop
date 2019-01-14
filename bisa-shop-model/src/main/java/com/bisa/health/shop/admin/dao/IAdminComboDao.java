package com.bisa.health.shop.admin.dao;

import java.util.List;

import com.bisa.health.shop.model.Combo;
import com.bisa.health.shop.model.Goods;


/**
 * 套餐表
 * @author Administrator
 */

public interface IAdminComboDao {

    /**
     * 更新套餐表 图片的信息
     * @param url
     * @param goodsNumber
     */

    int updateImg(String url, String goodsNumber);

    /**
     * 更新套餐表 商品的单价
     * @param goods
     * @return
     */
    int updateGoodsPriceByGoodsNumber(Goods goods);

    /**
     * 根据套餐的guid去查询 下面所有的套餐集合
     * @param comboGuid
     * @return
     */
    List<Combo> getComboByComboGuid(String comboGuid);

    /**
     * 新增combo对象
     * @param combo
     * @return
     */
    int insertCombo(Combo combo);

    /**
     * 删除套餐商品
     */
    int delCombo(String comboGuid);

    /**
     * 根据combo表ID查询对象
     * @param id
     * @return
     */
    Combo selectGoodsByComboId(int id);

    /**
     * 更改/编辑套餐商品价格
     */
    int updataCombo(float discount_price, int id);

    /**
     * 根据商品编号查询出套餐表的comboGuid
     * @param goods_number
     * @return
     */
    List<Combo> selectGoodsList(String goods_number);
}
