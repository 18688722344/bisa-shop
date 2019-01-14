package com.bisa.health.shop.dao;

import java.util.List;

import com.bisa.health.shop.model.Address;

/**
 * 地址表
 */
public interface IAddressDao {

    /**
     * 获取当前用户的 所有地址(user_guid)来查询
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
    boolean delAddress(int id);

    /**
     * 根据收货地址id查询
     * @param id
     * @return
     */
    Address selectAddressByAddressId(int id);

}
