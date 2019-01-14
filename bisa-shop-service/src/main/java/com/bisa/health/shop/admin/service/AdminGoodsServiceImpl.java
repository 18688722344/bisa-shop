package com.bisa.health.shop.admin.service;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.basic.entity.SystemContext;
import com.bisa.health.shop.admin.dao.IAdminGoodsDao;
import com.bisa.health.shop.admin.dto.OrderListPageDto;
import com.bisa.health.shop.enumerate.GoodsStatusEnum;
import com.bisa.health.shop.enumerate.NeedPostType;
import com.bisa.health.shop.model.Goods;
import com.bisa.health.shop.model.GoodsInternationalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "GoodsServiceImpl#${redis.exp1}")
public class AdminGoodsServiceImpl implements IAdminGoodsService {

    @Autowired
    private IAdminGoodsDao goodsDao;

    @Override
    @Cacheable(key = "targetClass.name+methodName+#goodsNumber")
    public Goods selectGoodsByGoodsNumber(String goodsNumber) {
        return goodsDao.selectGoodsByGoodsNumber(goodsNumber);
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#classifyId.toString()")
    public List<Goods> selectGoodsByClassifyId(Integer classifyId) {
        return goodsDao.selectGoodsByClassifyId(classifyId);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "GoodsInternationalizationServiceImpl#${redis.exp1}", allEntries = true),
            @CacheEvict(value = "GoodsServiceImpl#${redis.exp1}", allEntries = true),
    })
    public void updateGoods(Goods goods)  {
        goodsDao.updateGoods(goods);
    }


    @Override
    @Caching(evict = {
            @CacheEvict(value = "GoodsInternationalizationServiceImpl#${redis.exp1}", allEntries = true),
            @CacheEvict(value = "GoodsServiceImpl", allEntries = true),
    })
    public void updateGoodsImg(String url, String goodsNumber) {
        goodsDao.updateGoodsImg(url, goodsNumber);
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName")
    public List<Goods> loadMainGoodsObject() {
        return goodsDao.loadMainGoodsObject();
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName")
    public List<Goods> loadGoodsList() {
        return goodsDao.loadGoodsList();
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#id.toString()")
    public Goods getGoodsById(Integer id) {
        return goodsDao.getGoodsById(id);
    }

    @Override
    @CacheEvict(value = "GoodsServiceImpl#${redis.exp1}", allEntries = true)
    public boolean addGoods(Goods goods) {
        int result = goodsDao.addGoods(goods);
        return result >= 1 ? true : false;
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#page.toString() +#limit.toString() +#incontent +#searchabout +#val")
    public OrderListPageDto<Goods> selectAllGoods(Integer page, Integer limit, String incontent, String searchabout, String val) {
        // @param searchabout 1 商品编号 2 按年查询 和 3 按年-月查询 4 按年-月-日查询 (上架的时间)
        // @param val 0 全部 1 销售中 2 下架中 3 没货了 4 不用邮递 5 需要邮递
        SystemContext.setPageOffset((page - 1) * limit);
        SystemContext.setPageSize(limit);
        SystemContext.setSort("putaway_time"); // 默认按 上架的时间 倒序
        SystemContext.setOrder("desc");

        Pager<Goods> goodsPager = null;
        if (searchabout == null && val == null) {// (默认)查询全部
            goodsPager = goodsDao.selectPagerGoods();
        } else if ("1".equals(searchabout)) {// 根据 商品编号 查goods的信息
            goodsPager = goodsDao.selecGoodsByGoodsNumber(incontent);
        } else if ("2".equals(searchabout)) {// 根据 按年查询 查goods的信息
            goodsPager = goodsDao.selectGoodsByYear(incontent);
        } else if ("3".equals(searchabout)) {// 根据 按年-月 查goods的信息
            goodsPager = goodsDao.selectGoodsByYearMonth(incontent);
        } else if ("4".equals(searchabout)) {// 根据 按年-月-日 查goods的信息
            goodsPager = goodsDao.selectGoodsByYearMonthDay(incontent);
        } else if ("0".equals(val)) {// (默认)查询全部
            goodsPager = goodsDao.selectPagerGoods();
        } else if ("1".equals(val)) {// 1 销售中
            goodsPager = goodsDao.getGoodsByGoodsStatus(GoodsStatusEnum.in_sale.getValue());
        } else if ("2".equals(val)) {// 2 下架中
            goodsPager = goodsDao.getGoodsByGoodsStatus(GoodsStatusEnum.invalid.getValue());
        } else if ("3".equals(val)) {// 3 没货了
            goodsPager = goodsDao.getGoodsByGoodsStatus(GoodsStatusEnum.sale_out.getValue());
        } else if ("4".equals(val)) {// 3 不用邮递
            goodsPager = goodsDao.getGoodsByNeedPostTpye(NeedPostType.no_post.getValue());
        } else if ("5".equals(val)) {// 4 需要邮递
            goodsPager = goodsDao.getGoodsByNeedPostTpye(NeedPostType.need_post.getValue());
        }

        // 封装layui要的数据结构
        OrderListPageDto<Goods> listPageDto = new OrderListPageDto<>();
        listPageDto.setCode(0);
        listPageDto.setMsg("''");
        listPageDto.setCount(goodsPager.getTotal());
        listPageDto.setData(goodsPager.getDatas());
        return listPageDto;
    }

}
