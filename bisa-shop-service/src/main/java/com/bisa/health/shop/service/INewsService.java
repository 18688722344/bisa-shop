package com.bisa.health.shop.service;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.model.News;

import java.util.List;

/**
 * 新闻表
 */

public interface INewsService {

    /**
     * 上一篇，本文，下一篇文章
     *
     * @return
     * @param lang
     */
    List<News> getTop4ListNews(String lang);

    /**
     * 新闻分页
     *
     * @param page  分页页码0,10,20
     * @param limit 每页显示数量
     * @param lang 国际化语言
     * @return
     */
    Pager<News> getPagerNews(Integer page, Integer limit, String lang);

    /**
     * 更新新闻
     *
     * @param news
     * @return
     */
    boolean updateNews(News news);

    /**
     * 获取所有的新闻
     *
     * @return
     * @param lang
     */
    List<News> getListNews(String lang);

}
