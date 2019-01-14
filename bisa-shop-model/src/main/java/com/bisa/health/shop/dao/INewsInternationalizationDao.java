package com.bisa.health.shop.dao;

import com.bisa.health.shop.model.NewsInternationalization;

/**
 * 新闻 国际化表
 */

public interface INewsInternationalizationDao {

    /**
     * 根据新闻表的id获取 新闻国际化表的对象
     * @param id    新闻表的id
     * @param value 语言类型
     * @return
     */
    NewsInternationalization selectNewsInternationalizationByNewsId(int id, int value);
}
