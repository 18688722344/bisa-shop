package com.bisa.health.shop.dao;

import org.springframework.stereotype.Repository;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.model.Classify;

import java.util.List;

@Repository
public class ClassifyDaoImpl extends BaseDao<Classify> implements IClassifyDao {

    @Override
    public Classify selectClassifyById(Integer id) {
        String sql = "SELECT * FROM s_classify WHERE id = ?";
        return super.queryObjectBySql(sql, new Object[]{id}, Classify.class);
    }

    @Override
    public List<Classify> getClassifyListByHighest() {
        String sql = "SELECT * FROM s_classify where super_id = 0";
        return super.listBySql(sql, null, Classify.class);
    }
    @Override
    public List<Classify> getClassifyListBySuperId(Integer id) {
        String sql = "SELECT * FROM s_classify where super_id = ?";
        return super.listBySql(sql, new Object[]{id}, Classify.class);
    }
}
