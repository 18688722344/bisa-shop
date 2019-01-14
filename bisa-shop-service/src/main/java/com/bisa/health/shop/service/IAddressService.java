package com.bisa.health.shop.service;

import com.bisa.health.shop.model.Address;

import java.util.List;

/**
 * 地址表
 */
public interface IAddressService {

    /**
     * 查询用于所有收货地址
     * @param user_guid
     * @return
     */
    List<Address> selectAddressListByUserId(int user_guid);

    /**
     * 添加收货地址
     * @param address
     * @return
     */
    boolean addAddress(Address address);

    /**
     * 修改收货地址
     * @param address
     * @return
     */
    boolean updateAddress(Address address);

    /**
     * 删除收货地址
     * @param id
     * @return
     */
    boolean delAddress(Integer id);

    /**
     * 获取收货地址
     * @param id
     * @return
     */
    Address selectAddressByAddressId(int id);

}
