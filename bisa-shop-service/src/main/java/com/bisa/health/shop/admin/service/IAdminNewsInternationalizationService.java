package com.bisa.health.shop.admin.service;

import com.bisa.health.shop.model.NewsInternationalization;

public interface IAdminNewsInternationalizationService {

    /**
     * 添加新闻国际化
     * @param newsInternationalization
     * @return
     */
    NewsInternationalization addNewsInternationalization(NewsInternationalization newsInternationalization);

    /**
     * 加载新闻国际化信息
     * @param new_id
     * @param internationalization
     * @return
     */
    NewsInternationalization selectNewsInternation(int new_id,int internationalization);

    /**
     * 更新国际化新闻
     * @param newsInternationalization
     * @return
     */
    int updateNewsInternationalization(NewsInternationalization newsInternationalization);

    /**
     * 删除新闻
     * @param new_id
     * @return
     */
    boolean delNewsInternational(int new_id);
}
