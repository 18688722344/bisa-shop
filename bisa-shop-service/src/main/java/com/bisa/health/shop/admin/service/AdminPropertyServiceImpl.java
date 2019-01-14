package com.bisa.health.shop.admin.service;

import com.bisa.health.shop.admin.dao.IAdminPropertyDao;
import com.bisa.health.shop.admin.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "PropertyServiceImpl#${redis.exp1}")
public class AdminPropertyServiceImpl implements IAdminPropertyService {

    @Autowired
    private IAdminPropertyDao propertyDao;

    @Override
    @CacheEvict(value = "PropertyServiceImpl", allEntries = true)
    public void insertProperty(String property, Integer classifyId) {
        propertyDao.insertProperty(property, classifyId);
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#classifyId.toString()")
    public List<Property> selectPropertyByClassifyId(Integer classifyId) {
        List<Property> propertyPager = propertyDao.getListPropertyByClassifyId(classifyId);
        return propertyPager;
    }

    @Override
    @CacheEvict(value = "PropertyServiceImpl", allEntries = true)
    public boolean deleteProperty(int id) {
        int result = propertyDao.deleteProperty(id);
        return result >= 1 ? true : false;
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#id")
    public Property selectPropertyById(int id) {
        return propertyDao.selectPropertyById(id);
    }

    @Override
    @CacheEvict(value = "PropertyServiceImpl", allEntries = true)
    public boolean updateProperty(Property property) {
        int result = propertyDao.updateProperty(property);
        return result >= 1 ? true : false;
    }

}
