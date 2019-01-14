package com.bisa.health.shop.dao;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.model.News;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDaoImpl extends BaseDao<News> implements INewsDao {

    @Override
    public boolean updateNews(News news) {
        String sql = "UPDATE s_news SET read_quantity = ? WHERE id = ?";
        int result = super.updateBySql(sql, new Object[]{news.getRead_quantity(), news.getId()});
        return result >= 1 ? true : false;
    }

    @Override
    public Pager<News> getPagerNews() {
        String sql = "select * from s_news";
        return super.findBySql(sql, null, News.class, true);
    }

    @Override
    public List<News> getTop4ListNews() {
        String sql = "SELECT * FROM s_news ORDER BY READ_QUANTITY DESC LIMIT 0,4";
        return super.listBySql(sql, null, News.class);
    }

    @Override
    public List<News> getListNews() {
        String sql = "select * from s_news";
        return super.listBySql(sql, null, News.class);
    }

}
