package com.bisa.health.shop.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.model.Combo;
import com.bisa.health.shop.model.Goods;

@Repository
public class AdminComboDaoImpl extends BaseDao<Combo> implements IAdminComboDao {

    @Override
    public int updateImg(String url, String goodsNumber) {
        String sql = "UPDATE s_combo SET img_url = ? WHERE goods_number = ?";
        return super.updateBySql(sql, new Object[]{url, goodsNumber});
    }

    @Override
    public int updateGoodsPriceByGoodsNumber(Goods goods) {
        String sql = "UPDATE s_combo SET classify_id = ?,goods_name = ?,goods_price = ?,need_post = ? WHERE goods_number = ?";
        return super.updateBySql(sql, new Object[]{goods.getClassifyId(), goods.getGoodsName(), goods.getGoodsPrice(),
                goods.getGoodsNumber(), goods.getNeedPost()});
    }

    @Override
    public List<Combo> getComboByComboGuid(String comboGuid) {
        String sql = "SELECT * FROM s_combo WHERE combo_guid = ?";
        return super.listBySql(sql, new Object[]{comboGuid}, Combo.class);
    }

    @Override
    public int insertCombo(Combo combo) {
        String sql = "INSERT INTO s_combo(combo_guid, classify_id, goods_number, goods_name, count, goods_price, discount_price, img_url, need_post ) VALUES(?,?,?,?,?,?,?,?,?)";
        return super.addBySql(sql, new Object[]{combo.getComboGuid(), combo.getClassifyId(), combo.getGoodsNumber(), combo.getGoodsName(),
                combo.getCount(), combo.getGoodsPrice(), combo.getDiscountPrice(), combo.getImgUrl(), combo.getNeedPost()});
    }

    @Override
    public int delCombo(String comboGuid) {
        String sql = "DELETE FROM s_combo WHERE combo_guid= ?";
        return super.deleteBySql(sql, new Object[]{comboGuid});
    }

    @Override
    public Combo selectGoodsByComboId(int id) {
        String sql = "SELECT * FROM s_goods WHERE id = ?";
        return super.queryObjectBySql(sql, new Object[]{id}, Goods.class);
    }

    @Override
    public int updataCombo(float discount_price, int id) {
        String sql = "update s_combo SET discount_price = ?  where id = ?";
        return super.updateBySql(sql, new Object[]{discount_price, id});
    }

    public List<Combo> selectGoodsList(String goods_number){
        String sql = "SELECT * FROM s_combo WHERE goods_number = ?";
        return super.listBySql(sql, new Object[]{goods_number}, Combo.class);
    }
}
