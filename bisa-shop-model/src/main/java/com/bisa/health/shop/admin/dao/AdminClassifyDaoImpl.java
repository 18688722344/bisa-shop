package com.bisa.health.shop.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.model.Classify;

@Repository
public class AdminClassifyDaoImpl extends BaseDao<Classify> implements IAdminClassifyDao {

    @Override
    public Classify selectClassifyById(Integer id) {
        String sql = "SELECT * FROM s_classify WHERE id = ?";
        return super.queryObjectBySql(sql, new Object[]{id}, Classify.class);
    }

    @Override
    public int addClassify(Classify classfy) {
        String sql = "INSERT INTO s_classify(id, classify_name, super_id) VALUES (?, ?, ?)";
        return super.addBySql(sql, new Object[]{classfy.getId(), classfy.getClassifyName(), classfy.getSuperId()});
    }

    @Override
    public int delClassfiy(Integer id) {
        String sql = "DELETE FROM s_classify WHERE id = ?";
        return super.deleteBySql(sql, new Object[]{id});
    }

    @Override
    public int updateClassify(Integer id, String classifyName) {
        String sql = "UPDATE s_classify SET classify_name = ? WHERE id = ?";
        return super.updateBySql(sql, new Object[]{classifyName, id});
    }

    @Override
    public int insertClassify(Integer superId, Integer id, String classifyName) {
        String sql = "INSERT INTO s_classify(id, super_id,classify_name) VALUES (?,?,?)";
        return super.addBySql(sql, new Object[]{id, superId, classifyName});
    }

    @Override
    public List<Classify> getClassifyList() {
        String sql = "SELECT * FROM s_classify";
        return super.listBySql(sql, null, Classify.class);
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

    @Override
    public Integer getClassifyAcount() {
        String sql = "SELECT max(id) FROM s_classify";
        return (int) super.queryIntegerTotal(sql, null);
    }

}
