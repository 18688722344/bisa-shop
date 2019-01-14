package com.bisa.health.shop.admin.dao;

import java.util.List;

import com.bisa.health.shop.model.Classify;

/**
 * 分类表
 * @author Administrator
 */

public interface IAdminClassifyDao {

    /**
     * 获得指定类别
     * @param id
     * @return
     */
    Classify selectClassifyById(Integer id);

    /**
     * 添加类别
     * @param classfy
     * @return
     */
    int addClassify(Classify classfy);

    /**
     * 获取所有的分类信息
     * @return
     */
    List<Classify> getClassifyList();

    /**
     * 获取分类表的 最高级的信息
     * @return
     */
    List<Classify> getClassifyListByHighest();

    /**
     * 根据superId去查询 分类的集合
     * @param id
     * @return
     */
    List<Classify> getClassifyListBySuperId(Integer id);

    /**
     * 获取表的最大的id
     * @return
     */
    Integer getClassifyAcount();

    /**
     * 删除 类别 对应的id
     * @param id
     * @return
     */
    int delClassfiy(Integer id);

    /**
     * 修改类别 对象
     * @param id
     * @param classifyName
     * @return
     */
    int updateClassify(Integer id, String classifyName);

    /**
     * 新增 类别 对象
     * @param superId
     * @param id
     * @param classifyName
     * @return
     */
    int insertClassify(Integer superId, Integer id, String classifyName);

}
