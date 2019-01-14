package com.bisa.health.shop.admin.service;

import com.bisa.health.shop.admin.dto.OrderListPageDto;
import com.bisa.health.shop.model.News;

/**
 * news表
 * @author Administrator
 */
public interface IAdminNewsService {

    /**
     * 新闻表的id,加载新闻数据
     * @param id
     * @return
     */
    News getNewsById(int id);
    /**
     * 新闻表的news_id,加载新闻数据
     * @param news_id
     * @return
     */
    News getNewsByNewsId(int news_id);
    /**
     * 添加新闻
     * @param news
     * @return
     */
    News addNews(News news);

    /**
     * 更新新闻信息
     * @param news
     * @return
     */
    News updateNews(News news);

    /**
     * 删除新闻
     * @param id
     * @return
     */
    boolean deleteNews(int id);

    /**
     * 查询  (所有新闻)
     * @param page        第几页
     * @param limit       每页有多少数据
     * @param incontent   input输入的内容   和下面对应
     * @param searchabout option选择   1 文章标题  2 文章ID
     * @return
     */
    OrderListPageDto<News> selectAllNews(Integer page, Integer limit, String incontent, String searchabout);

}
