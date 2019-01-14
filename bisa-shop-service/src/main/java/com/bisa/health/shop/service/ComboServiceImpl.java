package com.bisa.health.shop.service;

import com.bisa.health.shop.dao.IComboDao;
import com.bisa.health.shop.model.Combo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@CacheConfig(cacheNames = "ComboServiceImpl#${redis.exp1}")
public class ComboServiceImpl implements IComboService {

    @Resource
    private IComboDao comboDao;

    @Override
    @Cacheable(key = "targetClass.name+methodName+#comboGuid")
    public List<Combo> selectComboByGuid(String comboGuid) {
        return comboDao.selectComboByGuid(comboGuid);
    }

}
