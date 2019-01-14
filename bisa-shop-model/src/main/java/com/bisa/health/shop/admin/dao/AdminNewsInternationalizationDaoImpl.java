package com.bisa.health.shop.admin.dao;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.model.NewsInternationalization;
import org.springframework.stereotype.Repository;


@Repository
public class AdminNewsInternationalizationDaoImpl extends BaseDao<NewsInternationalization> implements IAdminNewsInternationalizationDao {

    @Override
    public NewsInternationalization addNewsInternationalization(NewsInternationalization newsInternationalization) {
        String sql = "insert into s_new_internationalization(new_id,internationalization,img_url,author,news_content,news_subhead,news_title) values(?,?,?,?,?,?,?)";
        int result =  super.addBySql(sql, new Object[]{newsInternationalization.getNew_id(),newsInternationalization.getInternationalization(),newsInternationalization.getImg_url(),
                newsInternationalization.getAuthor(),newsInternationalization.getNews_content(),newsInternationalization.getNews_subhead(),newsInternationalization.getNews_title()});
        return result >= 1 ? newsInternationalization : null;
    }
    @Override
    public NewsInternationalization selectNewsInternation(int new_id,int internationalization){
        String sql = "select * from s_new_internationalization where new_id=? and internationalization=?";
        return super.queryObjectBySql(sql, new Object[]{new_id,internationalization}, NewsInternationalization.class);
    }
    @Override
    public int updateNewsInternationalization(NewsInternationalization newsInternationalization){
        String sql = "update s_new_internationalization set img_url=?,author=?,news_content=?,news_subhead=?,news_title=? where new_id=? and internationalization=?";
        return super.updateBySql(sql, new Object[]{newsInternationalization.getImg_url(),newsInternationalization.getAuthor(),newsInternationalization.getNews_content(),
                newsInternationalization.getNews_subhead(),newsInternationalization.getNews_title(),newsInternationalization.getNew_id(),newsInternationalization.getInternationalization()});
    }
    @Override
    public boolean delNewsInternational(int new_id) {
        String sql = "DELETE FROM s_new_internationalization WHERE new_id=?";
        int result = super.deleteBySql(sql, new Object[]{new_id});
        return result >= 1 ? true : false;
    }
}
