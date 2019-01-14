package com.bisa.health.shop.service;

import com.bisa.health.basic.entity.Pager;
import com.bisa.health.shop.dao.IUserAppraiseDao;
import com.bisa.health.shop.model.UserAppraise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "UserAppraiseServiceImpl#${redis.exp1}")
public class UserAppraiseServiceImpl implements IUserAppraiseService {

    @Autowired
    private IUserAppraiseDao userAppraiseDao;

    @Override
    @Cacheable(key = "targetClass.name+methodName+#goodsNumber")
    public Pager<UserAppraise> getPagerAppraiseByGoodsNumber(String goodsNumber) {
        return userAppraiseDao.getPagerAppraiseByGoodsNumber(goodsNumber);
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#userGuid.toString()")
    public Pager<UserAppraise> getPagerAppraiseByUserGuid(Integer userGuid) {
        return userAppraiseDao.getPagerAppraiseByUserGuid(userGuid);
    }

    @Override
    @CacheEvict(value = "UserAppraiseServiceImpl", allEntries = true)
    public boolean addUserAppraise(UserAppraise userAppraise) {
        int result = userAppraiseDao.addUserAppraise(userAppraise);
        return result >= 1 ? true : false;
    }

    @Override
    @CacheEvict(value = "UserAppraiseServiceImpl", allEntries = true)
    public boolean updateUserAppraise(UserAppraise userAppraise) {
        int result = userAppraiseDao.updateUserAppraise(userAppraise);
        return result >= 1 ? true : false;
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#id.toString()")
    public UserAppraise getAppraiseById(Integer id) {
        return userAppraiseDao.getAppraiseById(id);
    }

}
