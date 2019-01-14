package com.bisa.health.shop.dao;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.model.NewsInternationalization;
import org.springframework.stereotype.Repository;

@Repository
public class NewsInternationalizationDaoImpl extends BaseDao<NewsInternationalization> implements INewsInternationalizationDao {

    @Override
    public NewsInternationalization selectNewsInternationalizationByNewsId(int id, int value) {
        String sql = "SELECT * FROM s_new_internationalization WHERE new_id = ? and internationalization = ?";
        return super.queryObjectBySql(sql, new Object[]{id, value}, NewsInternationalization.class);
    }
}
