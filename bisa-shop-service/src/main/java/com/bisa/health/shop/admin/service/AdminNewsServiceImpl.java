package com.bisa.health.shop.admin.service;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.basic.entity.SystemContext;
import com.bisa.health.shop.admin.dao.IAdminNewsDao;
import com.bisa.health.shop.admin.dto.OrderListPageDto;
import com.bisa.health.shop.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "NewsServiceImpl#${redis.exp1}")
public class AdminNewsServiceImpl implements IAdminNewsService {

    @Autowired
    private IAdminNewsDao iNewsDao;

    @Override
    public News getNewsById(int id) {
        return iNewsDao.getNewsById(id);
    }
    @Override
    public News getNewsByNewsId(int news_id) {
        return iNewsDao.getNewsByNewsId(news_id);
    }

    @Override
    @CacheEvict(value = "NewsServiceImpl", allEntries = true)
    public News addNews(News news) {
        return iNewsDao.addNews(news);
    }

    @Override
    @CacheEvict(value = "NewsServiceImpl", allEntries = true)
    public News updateNews(News news) {
        iNewsDao.updateNews(news);
        return news;
    }

    @Override
    @CacheEvict(value = "NewsServiceImpl", allEntries = true)
    public boolean deleteNews(int id) {
        return iNewsDao.deleteNews(id);
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#page.toString()+# limit.toString()+#incontent+#searchabout")
    public OrderListPageDto<News> selectAllNews(Integer page, Integer limit, String incontent, String searchabout) {
        // * @param incontent input输入的内容 和下面对应
        // * @param searchabout option选择 1 文章标题 2 文章ID
        SystemContext.setPageOffset((page - 1) * limit);
        SystemContext.setPageSize(limit);
        SystemContext.setSort("release_time"); // 默认按订单时间倒序
        SystemContext.setOrder("desc");

        // 高级查询
        Pager<News> pagerOrder = null;
        if (searchabout == null) {
            pagerOrder = iNewsDao.getPagerNews();
        } else if (searchabout.equals("1")) {// 1 文章标题
            pagerOrder = iNewsDao.selectNewsByArticleTitle(incontent);
        } else if (searchabout.equals("2")) {// 2 文章ID
            pagerOrder = iNewsDao.selectNewsByArticleID(incontent);
        }

        // 封装layui要的数据结构
        OrderListPageDto<News> listPageDto = new OrderListPageDto<>();
        listPageDto.setCode(0);
        listPageDto.setMsg("''");
        listPageDto.setCount(pagerOrder.getTotal());
        listPageDto.setData(pagerOrder.getDatas());
        return listPageDto;
    }

}
