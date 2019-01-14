package com.bisa.health.shop.dao;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.model.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GoodsDaoImpl extends BaseDao<Goods> implements IGoodsDao {

    public Goods selectGoodsByGoodsNumber(String goods_number) {
        String sql = "SELECT * FROM s_goods WHERE goods_number = ?";
        return super.selectObjectBySql(sql, new Object[]{goods_number}, Goods.class);
    }

    @Override
    public int updateAppraiseNumber(Goods goods) {
        String sql = "UPDATE s_goods SET appraise_number = ? WHERE id = ?";
        return super.updateBySql(sql, new Object[]{goods.getAppraiseNumber(), goods.getId()});
    }

    @Override
    public List<Goods> listChildrenGoods(Integer id) {
        String sql = "SELECT * FROM s_goods WHERE parent_id = ?";
        return super.listBySql(sql, new Object[]{id}, Goods.class);
    }

}
