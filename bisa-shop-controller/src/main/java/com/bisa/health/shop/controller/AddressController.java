package com.bisa.health.shop.controller;

import com.bisa.health.client.entity.User;
import com.bisa.health.shiro.web.bind.CurrentUser;
import com.bisa.health.common.enumerate.ExceptionCodeEnum;
import com.bisa.health.shop.model.Address;
import com.bisa.health.shop.service.IAddressService;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户地址
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/user")
public class AddressController {

    @Resource
    private IAddressService addressService;

    /**
     * 用户中心  查看收货地址页面
     * @return
     */
    @RequestMapping(value = "/userAddress", method = RequestMethod.GET)
    public String requestReport() {
        return "user-center/userAddress";
    }

    /**
     * 异步加载用户的地址信息
     * @return
     */
    @ResponseBody
    public List<Address> loadUserAddresses(@CurrentUser User sysUser) {
        List<Address> addressList = addressService.selectAddressListByUserId(sysUser.getUser_guid());
        return addressList;
    }

    /**
     * 添加地址
     * @return
     */
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    @ResponseBody
    public String addAddress(String addressJson, @CurrentUser User sysUser) {

        JSONObject jsonObj = new JSONObject(addressJson);
        String name = jsonObj.getString("name");
        String tel = jsonObj.getString("tel");
        String province = jsonObj.getString("input_province");
        String area = jsonObj.getString("area");

        Address address = new Address();
        address.setUser_id(sysUser.getUser_guid());
        address.setConsignee(name);
        address.setPhone(tel);
        address.setCountry("");
        address.setProvince(province);
        address.setCity(jsonObj.getString("input_city"));
        address.setCounty(jsonObj.getString("input_county"));
        address.setTown("");
        address.setDetail_address(area);
        address.setAddress_label(jsonObj.getString("address_label"));
        address.setIs_default(jsonObj.getString("is_default"));

        try {
            addressService.addAddress(address);
            return ExceptionCodeEnum.SUCCESS.getValue();
        } catch (Exception e) {
            return ExceptionCodeEnum.FAILURE.getValue();
        }
    }

    /**
     * 更新地址
     * @return
     */
    @RequestMapping(value = "/updateAddress", method = RequestMethod.POST)
    @ResponseBody
    public String updateAddress(String addressJson, @CurrentUser User sysUser) {

        JSONObject jsonObj = new JSONObject(addressJson);
        String add_id = jsonObj.getString("id");
        String name = jsonObj.getString("name");
        String tel = jsonObj.getString("tel");
        String province = jsonObj.getString("input_province");
        String area = jsonObj.getString("area");

        try {
            //获取修改之前的对象
            Address address = addressService.selectAddressByAddressId(Integer.parseInt(add_id));

            address.setConsignee(name);
            address.setPhone(tel);
            address.setCountry(address.getCountry());
            address.setProvince(province);
            address.setCity(jsonObj.getString("input_city"));
            address.setCounty(jsonObj.getString("input_county"));
            address.setTown(address.getTown());
            address.setDetail_address(area);
            address.setAddress_label(jsonObj.getString("address_label"));
            address.setIs_default(jsonObj.getString("is_default"));
            addressService.updateAddress(address);

            return ExceptionCodeEnum.SUCCESS.getValue();
        } catch (Exception e) {
            return ExceptionCodeEnum.FAILURE.getValue();
        }
    }

    /**
     * 删除地址
     * @param id 地址表的id
     * @return
     */
    @RequestMapping(value = "/delAddress", method = RequestMethod.POST)
    @ResponseBody
    public String delAddress(Integer id) {

        try {
            boolean delResult = addressService.delAddress(id);
            return ExceptionCodeEnum.SUCCESS.getValue();
        } catch (Exception e) {
            return ExceptionCodeEnum.FAILURE.getValue();
        }
    }

}
