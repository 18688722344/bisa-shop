package com.bisa.health.shop.admin.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bisa.health.common.enumerate.ExceptionCodeEnum;
import com.bisa.health.common.utils.RandomUtils;
import com.bisa.health.shop.admin.dto.treeGridDataDto;
import com.bisa.health.shop.admin.model.Property;
import com.bisa.health.shop.admin.model.PropertyValue;
import com.bisa.health.shop.admin.service.IAdminClassifyService;
import com.bisa.health.shop.admin.service.IAdminPropertyService;
import com.bisa.health.shop.admin.service.IAdminPropertyValueService;
import com.bisa.health.shop.admin.util.JsonResult;
import com.bisa.health.shop.model.Classify;


/**
 * 类别、属性和标签管理
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/admin/commodity")
@RequiresRoles(value = {"ROLE_ADMIN", "ROLE_CUSTOMER"}, logical = Logical.OR)
public class AdminClassifyPropertyTagController {

    @Autowired
    private IAdminPropertyService propertyService;
    @Autowired
    private IAdminPropertyValueService propertyValueService;
    @Autowired
    private IAdminClassifyService classifyService;

    /**
     * 类别 管理页面
     * @return
     */
    @RequestMapping(value = "/classifyView", method = RequestMethod.GET)
    public String classifyView() {
        return "admin/admin_commodity/classifyView";
    }

    /**
     * 类别 管理页面,加载类别表的所有信息
     * @return
     */
    @RequestMapping(value = "/classifyInfo")
    @ResponseBody
    public treeGridDataDto<Classify> classifyInfo() {

        treeGridDataDto<Classify> treeGridDataDto = classifyService.classifyInfo();
        return treeGridDataDto;
    }

    /**
     * 类别 管理页面,删除类别对应的id和他下面的类别
     * @param id 分类表的id
     * @return
     */
    @RequestMapping(value = "/delClassify")
    @ResponseBody
    public JsonResult delClassify(Integer id) {

        JsonResult jsonResult = new JsonResult();
        try {
            classifyService.delClassfiy(id);
            jsonResult.setFlag(true);
        } catch (Exception e) {
            jsonResult.setFlag(false);
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 类别 管理页面,修改类别对象
     * @param id           分类表的id
     * @param classifyName 改后分类名称
     * @return
     */
    @RequestMapping(value = "/updateClassify")
    @ResponseBody
    public JsonResult updateClassify(Integer id,String classifyName) {

        JsonResult jsonResult = new JsonResult();
        try {
            classifyService.updateClassify(id, classifyName);
            jsonResult.setFlag(true);
        } catch (Exception e) {
            jsonResult.setFlag(false);
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 类别 管理页面,新增 类别对象
     * @param superId      新增数据关联的父类id
     * @param id           分类表的id
     * @param classifyName 分类的名称
     * @return
     */
    @RequestMapping(value = "/insertClassify")
    @ResponseBody
    public JsonResult insertClassify(Integer superId,Integer id,String classifyName) {

        JsonResult jsonResult = new JsonResult();
        try {
            classifyService.insertClassify(superId, id, classifyName);
            jsonResult.setFlag(true);
        } catch (Exception e) {
            jsonResult.setFlag(false);
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 添加属性
     * @param classifyId 分类表id
     * @param classifyId 属性名字
     * @return
     */
    @RequestMapping(value = "/addProperty", method = RequestMethod.POST)
    @ResponseBody
    public String addProperty(Integer classifyId,String propertyName) {

        try {
            propertyService.insertProperty(propertyName, classifyId);
            return ExceptionCodeEnum.SUCCESS.getValue();
        } catch (Exception e) {
            e.printStackTrace();
            return ExceptionCodeEnum.FAILURE.getValue();
        }
    }

    /**
     * 属性 列表 页面
     * @param classifyId 分类id
     * @return
     */
    @RequestMapping(value = "/propertyView", method = RequestMethod.GET)
    public String propertyView(Integer classifyId, Model model) {

        Classify classify = classifyService.selectClassifyById(classifyId);
        if (classify != null) {
            model.addAttribute("classifyName", classify.getClassifyName());
            model.addAttribute("classifyId", classifyId);
        }
        return "admin/admin_commodity/propertyView";
    }

    /**
     * 编辑属性
     * @param classifyId   分类id
     * @param propertyName 属性的名称
     * @return
     */
    @RequestMapping(value = "/editProperty", method = RequestMethod.POST)
    @ResponseBody
    public String editProperty(Integer classifyId,String propertyName) {

        Property property = propertyService.selectPropertyById(classifyId);
        if (property != null) {
            property.setName(propertyName);
            boolean result = propertyService.updateProperty(property);
            if (result) {
                return ExceptionCodeEnum.SUCCESS.getValue();
            }
        }
        return ExceptionCodeEnum.FAILURE.getValue();
    }

    /**
     * 删除属性
     * @param classifyId 分类id
     * @return
     */
    @RequestMapping(value = "/deleteProperty", method = RequestMethod.POST)
    @ResponseBody
    public String deleteProperty(Integer classifyId) {

        boolean result = propertyService.deleteProperty(classifyId);
        if (result) {
            return ExceptionCodeEnum.SUCCESS.getValue();
        }
        return ExceptionCodeEnum.FAILURE.getValue();
    }

    /**
     * 获得某个类别所有属性
     * @param classifyId 分类id
     * @return
     */
    @RequestMapping(value = "/selectPropertyList", method = RequestMethod.GET)
    @ResponseBody
    public List<Property> selectPropertyList(Integer classifyId) {

        List<Property> propertyList = propertyService.selectPropertyByClassifyId(classifyId);
        return propertyList;
    }

    /**
     * 获得某个属性的所有属性值
     * @param propertyId 属性的id
     * @return
     */
    @RequestMapping(value = "/selectPropertyValues", method = RequestMethod.GET)
    @ResponseBody
    public List<PropertyValue> selectPropertyValues(Integer propertyId) {

        List<PropertyValue> propertyList = propertyValueService.selectPropertyValues(propertyId);
        return propertyList;
    }

    /**
     * 根据标签ID删除标签
     * @param propertyValueId 标签id
     * @return
     */
    @RequestMapping(value = "/deletePropertyValues", method = RequestMethod.POST)
    @ResponseBody
    public String deletePropertyValues(Integer id) {

        try {
            boolean result = propertyValueService.deletePropertyValues(id);
            if (result) {
                return ExceptionCodeEnum.SUCCESS.getValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ExceptionCodeEnum.FAILURE.getValue();
    }

    /**
     * 新增 ,商品的标签
     * @param propertyId   属性id
     * @param propertyName 标签名称
     * @return
     */
    @RequestMapping(value = "/insertPropertyValue", method = RequestMethod.POST)
    @ResponseBody
    public String insertPropertyValue(Integer propertyId, String propertyName) {

        try {
            String valueId = RandomUtils.randomSixNum().substring(0, 4);
            boolean result = propertyValueService.insertPropertyValue(propertyId, valueId, propertyName);
            if (result) {
                return ExceptionCodeEnum.SUCCESS.getValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ExceptionCodeEnum.FAILURE.getValue();
    }

    /**
     * 更改编辑属性值
     * @param id            属性id
     * @param propertyValue 属性值
     * @return
     */
    @RequestMapping(value = "/updataPropertyValue", method = RequestMethod.POST)
    @ResponseBody
    public String updataPropertyValue( String propertyValue,Integer id) {

        try {
            boolean result = propertyValueService.updataPropertyValue(propertyValue, id);
            if (result) {
                return ExceptionCodeEnum.SUCCESS.getValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ExceptionCodeEnum.FAILURE.getValue();
    }

}
