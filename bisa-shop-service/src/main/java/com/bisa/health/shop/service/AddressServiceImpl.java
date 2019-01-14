package com.bisa.health.shop.service;

import com.bisa.health.shop.dao.IAddressDao;
import com.bisa.health.shop.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "AddressServiceImpl#${redis.exp1}")
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private IAddressDao iAddressDao;

    @Override
    @Cacheable(key = "targetClass.name+methodName+#user_guid")
    public List<Address> selectAddressListByUserId(int user_guid) {
        return iAddressDao.selectAddressListByUserId(user_guid);
    }

    @Override
    @CacheEvict(value = "AddressServiceImpl", allEntries = true)
    public boolean addAddress(Address address) {
        return iAddressDao.addAddress(address);
    }

    @Override
    @CacheEvict(value = "AddressServiceImpl", allEntries = true)
    public boolean updateAddress(Address address) {
        return iAddressDao.updateAddress(address);
    }

    @Override
    @CacheEvict(value = "AddressServiceImpl", allEntries = true)
    public boolean delAddress(Integer id) {
        return iAddressDao.delAddress(id);
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#id")
    public Address selectAddressByAddressId(int id) {
        return iAddressDao.selectAddressByAddressId(id);
    }

}
