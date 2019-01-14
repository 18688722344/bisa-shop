package com.bisa.health.shop.dao;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.model.News;

import java.util.List;

/**
 * 新闻表
 */

public interface INewsDao {

    /**
     * 新闻列表的新闻分页
     * @return
     */
    Pager<News> getPagerNews();

    /**
     * 更新新闻
     * @param news
     * @return
     */
    boolean updateNews(News news);

    /**
     * 上一篇，本文，下一篇文章
     * @return
     */
    List<News> getTop4ListNews();

    /**
     * 获取所有的新闻
     * @return
     */
    List<News> getListNews();

}
