package com.bisa.health.shop.service;


import com.bisa.health.shop.dao.IClassifyDao;
import com.bisa.health.shop.model.Classify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "ClassifyServiceImpl#${redis.exp1}")
public class ClassifyServiceImpl implements IClassifyService {

    @Autowired
    private IClassifyDao classifyDao;

    @Override
    @Cacheable(key = "targetClass.name+methodName+#id.toString()")
    public Classify selectClassifyById(Integer id) {
        return classifyDao.selectClassifyById(id);
    }

    @Override
    public List<Classify> getClassifyListByHighest(){
        return classifyDao.getClassifyListByHighest();
    }

    @Override
    public List<Classify>  getClassifyListBySuperId(Integer id){
        return classifyDao.getClassifyListBySuperId(id);
    }
}
