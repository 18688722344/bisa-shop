package com.bisa.health.shop.admin.dao;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.model.News;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminNewsDaoImpl extends BaseDao<News> implements IAdminNewsDao {

    @Override
    public News getNewsById(int id) {
        String sql = "SELECT * FROM s_news WHERE ID=?";
        return super.queryObjectBySql(sql, new Object[]{id}, News.class);
    }
    @Override
    public News getNewsByNewsId(int news_id) {
        String sql = "SELECT * FROM s_news WHERE news_id=?";
        return super.queryObjectBySql(sql, new Object[]{news_id}, News.class);
    }
    @Override
    public News addNews(News news) {
        String sql = "INSERT INTO s_news(NEWS_ID,NEWS_TITLE,NEWS_SUBHEAD,READ_QUANTITY,NEWS_CONTENT,IMG_URL,RELEASE_TIME,AUTHOR) VALUES(?,?,?,?,?,?,?,?)";
        int result = super.addBySql(sql, new Object[]{news.getNews_id(), news.getNews_title(), news.getNews_subhead(),
                news.getRead_quantity(), news.getNews_content(), news.getImg_url(), news.getRelease_time(), news.getAuthor()});
        return result >= 1 ? news : null;
    }

    @Override
    public boolean updateNews(News news) {
        String sql = "UPDATE s_news SET NEWS_ID=?,NEWS_TITLE=?,NEWS_SUBHEAD=?,READ_QUANTITY=?,NEWS_CONTENT=?,IMG_URL=?,RELEASE_TIME=?,AUTHOR=? WHERE ID=?";
        int result = super.updateBySql(sql, new Object[]{news.getNews_id(), news.getNews_title(), news.getNews_subhead(), news.getRead_quantity(),
                news.getNews_content(), news.getImg_url(), news.getRelease_time(), news.getAuthor(), news.getId()});
        return result >= 1 ? true : false;
    }

    @Override
    public boolean deleteNews(int id) {
        String sql = "DELETE FROM s_news WHERE ID=?";
        int result = super.deleteBySql(sql, new Object[]{id});
        return result >= 1 ? true : false;
    }

    @Override
    public Pager<News> getPagerNews() {
        String sql = "select * from s_news";
        return super.findBySql(sql, null, News.class, true);
    }

    @Override
    public Pager<News> selectNewsByArticleTitle(String incontent) {
        String sql = "select * from s_news where news_title = ?";
        return super.findBySql(sql, new Object[]{incontent}, News.class, true);
    }

    @Override
    public Pager<News> selectNewsByArticleID(String incontent) {
        String sql = "select * from s_news where id = ?";
        return super.findBySql(sql, new Object[]{incontent}, News.class, true);
    }

}
