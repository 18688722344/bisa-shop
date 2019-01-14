package com.bisa.health.shop.dao;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.model.Combo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComboDaoImpl extends BaseDao<Combo> implements IComboDao {

    @Override
    public List<Combo> selectComboByGuid(String comboGuid) {
        String sql = "SELECT * FROM s_combo WHERE combo_guid = ?";
        return super.listBySql(sql, new Object[]{comboGuid}, Combo.class);
    }

}
