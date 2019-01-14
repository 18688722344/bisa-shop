package com.bisa.health.shop.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bisa.health.shop.admin.dao.IAdminPropertyValueDao;
import com.bisa.health.shop.admin.model.PropertyValue;

@Service
@CacheConfig(cacheNames = "PropertyValueServiceImpl#${redis.exp1}")
public class AdminPropertyValueServiceImpl implements IAdminPropertyValueService {

    @Autowired
    private IAdminPropertyValueDao propertyValueDao;

    @Override
    @Cacheable(key = "targetClass.name+methodName")
    public Integer getValueIdMax() {
        return propertyValueDao.getValueIdMax();
    }

    @Override
    @CacheEvict(value = "PropertyValueServiceImpl", allEntries = true)
    public boolean insertPropertyValue(int property_id, String value_id, String name) {
        int result = propertyValueDao.insertPropertyValue(property_id, value_id, name);
        return result >= 1 ? true : false;
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#propertyId.toString()")
    public List<PropertyValue> selectPropertyValues(Integer propertyId) {
        return propertyValueDao.selectPropertyValues(propertyId);
    }

    @Override
    @CacheEvict(value = "PropertyValueServiceImpl", allEntries = true)
    public boolean deletePropertyValues(int id) {
        int result = propertyValueDao.deletePropertyValues(id);
        return result >= 1 ? true : false;
    }

    @Override
    @CacheEvict(value = "PropertyValueServiceImpl", allEntries = true)
    public boolean updataPropertyValue(String name, int id) {
        int result = propertyValueDao.updataPropertyValue(name, id);
        return result >= 1 ? true : false;
    }

}
