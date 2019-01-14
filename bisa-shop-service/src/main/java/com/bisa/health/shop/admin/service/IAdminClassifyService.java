package com.bisa.health.shop.admin.service;

import java.util.List;

import com.bisa.health.shop.admin.dto.FormSelectsDto;
import com.bisa.health.shop.admin.dto.treeGridDataDto;
import com.bisa.health.shop.model.Classify;

/**
 * 分类表
 * @author Administrator
 */

public interface IAdminClassifyService {

    /**
     * 获取 指定类别
     * @return
     */
    Classify selectClassifyById(Integer id);

    /**
     * 添加类别
     * @return
     */
    boolean addClassfiy(Classify classfy);

    /**
     * 加载商品分类 信息  (新增商品页面)
     * @param goodsClassify
     */
    FormSelectsDto getClassifyList(Integer goodsClassify);

    /**
     * 加载商品 标签 信息  (新增商品页面)
     * @param goodsNumber
     * @return
     */
    FormSelectsDto getGoodsTag(String goodsNumber);

    /**
     * （类别 管理页面） 加载类别表的所有信息
     * @return
     */
    treeGridDataDto<Classify> classifyInfo();

    /**
     * 删除 类别  对应的id
     * @param id
     */
    void delClassfiy(Integer id);

    /**
     * 修改类别 对象
     * @param id
     * @param classifyName
     */
    void updateClassify(Integer id, String classifyName);

    /**
     * 新增 类别 对象
     * @param superId
     * @param classifyName
     */
    void insertClassify(Integer superId, Integer id, String classifyName);

}
