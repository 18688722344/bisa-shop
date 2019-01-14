package com.bisa.health.shop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bisa.health.basic.dao.BaseDao;
import com.bisa.health.shop.model.Address;

@Repository
public class AddressDaoImpl extends BaseDao<Address> implements IAddressDao {

    @Override
    public List<Address> selectAddressListByUserId(int user_guid) {
        String sql = "SELECT * FROM s_address WHERE user_id = ?";
        return super.listBySql(sql, new Object[]{user_guid}, Address.class);
    }

    @Override
    public boolean addAddress(Address address) {
        String sql = "INSERT INTO s_address(user_id, consignee, phone, country, province,"
                + " city, county, town, detail_address, address_label, is_default)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = super.addBySql(sql, new Object[]{address.getUser_id(), address.getConsignee(), address.getPhone(), address.getCountry(), address.getProvince(),
                address.getCity(), address.getCounty(), address.getTown(), address.getDetail_address(), address.getAddress_label(), address.getIs_default()});
        return result >= 1 ? true : false;
    }

    @Override
    public boolean updateAddress(Address address) {
        String sql = "UPDATE s_address SET consignee=?, phone=?, country=?, province=?, city=?,"
                + " county=?, town=?, detail_address=?, address_label=?, is_default=? WHERE id=?";
        int result = super.updateBySql(sql, new Object[]{address.getConsignee(), address.getPhone(), address.getCountry(), address.getProvince(), address.getCity(),
                address.getCounty(), address.getTown(), address.getDetail_address(), address.getAddress_label(), address.getIs_default(), address.getId()});
        return result >= 1 ? true : false;
    }

    @Override
    public boolean delAddress(int id) {
        String sql = "DELETE FROM s_address WHERE id=?";
        int result = super.deleteBySql(sql, new Object[]{id});
        return result >= 1 ? true : false;
    }

    @Override
    public Address selectAddressByAddressId(int id) {
        String sql = "SELECT * FROM s_address WHERE id = ?";
        return super.queryObjectBySql(sql, new Object[]{id}, Address.class);
    }

}
