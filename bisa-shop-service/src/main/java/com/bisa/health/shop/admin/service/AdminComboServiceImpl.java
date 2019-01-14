package com.bisa.health.shop.admin.service;

import com.bisa.health.shop.admin.dao.IAdminComboDao;
import com.bisa.health.shop.model.Combo;
import com.bisa.health.shop.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "ComboServiceImpl#${redis.exp1}")
public class AdminComboServiceImpl implements IAdminComboService {

    @Autowired
    private IAdminComboDao comboDao;

    @Override
    @CacheEvict(value = "ComboServiceImpl", allEntries = true)
    public void updateImg(String url, String goodsNumber) {
        comboDao.updateImg(url, goodsNumber);
    }

    @Override
    @CacheEvict(value = "ComboServiceImpl", allEntries = true)
    public void updateGoodsPriceByGoodsNumber(Goods goods) {
        comboDao.updateGoodsPriceByGoodsNumber(goods);
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#comboGuid")
    public List<Combo> getComboByComboGuid(String comboGuid) {
        List<Combo> list = comboDao.getComboByComboGuid(comboGuid);
        return list;
    }

    @Override
    @CacheEvict(value = "ComboServiceImpl", allEntries = true)
    public void insertCombo(Combo combo) {
        comboDao.insertCombo(combo);
    }

    @Override
    @CacheEvict(value = "ComboServiceImpl", allEntries = true)
    public boolean delCombo(String comboGuid) {
        int result = comboDao.delCombo(comboGuid);
        return result >= 1 ? true : false;
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#id")
    public Combo selectCombosByComboId(int id) {
        return comboDao.selectGoodsByComboId(id);
    }

    @Override
    @CacheEvict(value = "ComboServiceImpl", allEntries = true)
    public boolean updataCombo(float discount_price, int id) {
        int result = comboDao.updataCombo(discount_price, id);
        return result >= 1 ? true : false;
    }

    @Override
    @CacheEvict(value = "ComboServiceImpl", allEntries = true)
    public List<Combo> selectGoodsList(String goods_number){
        return comboDao.selectGoodsList(goods_number);
    }
}
