package com.bisa.health.shop.admin.service;

import com.bisa.health.shop.admin.dao.IAdminClassifyDao;
import com.bisa.health.shop.admin.dao.IAdminGoodsPropertyDao;
import com.bisa.health.shop.admin.dao.IAdminPropertyValueDao;
import com.bisa.health.shop.admin.dto.FormSelectsDto;
import com.bisa.health.shop.admin.dto.LayuiLinkageDto;
import com.bisa.health.shop.admin.dto.treeGridDataDto;
import com.bisa.health.shop.admin.model.GoodsProperty;
import com.bisa.health.shop.admin.model.PropertyValue;
import com.bisa.health.shop.admin.util.GoodsTagUtils;
import com.bisa.health.shop.model.Classify;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "ClassifyServiceImpl#${redis.exp1}")
public class AdminClassifyServiceImpl implements IAdminClassifyService {

    @Autowired
    private IAdminClassifyDao classifyDao;
    @Autowired
    private IAdminPropertyValueDao propertyValueDao;
    @Autowired
    private IAdminGoodsPropertyDao goodsPropertyDao;
    

    @Override
    @Cacheable(key = "targetClass.name+methodName+#id.toString()")
    public Classify selectClassifyById(Integer id) {
        return classifyDao.selectClassifyById(id);
    }

    @Override
    @CacheEvict(value = "ClassifyServiceImpl", allEntries = true)
    public boolean addClassfiy(Classify classfy) {
        int result = classifyDao.addClassify(classfy);
        return result >= 1 ? true : false;
    }

    @Override
    @CacheEvict(value = "ClassifyServiceImpl", allEntries = true)
    public void insertClassify(Integer superId, Integer id, String classifyName) {
        classifyDao.insertClassify(superId, id, classifyName);
    }

    @Override
    @CacheEvict(value = "ClassifyServiceImpl", allEntries = true)
    public void updateClassify(Integer id, String classifyName) {
        classifyDao.updateClassify(id, classifyName);
    }

    @Override
    @CacheEvict(value = "ClassifyServiceImpl", allEntries = true)
    public void delClassfiy(Integer id) {
        delRecursion(id);
    }

    // 删除 分类的递归
    private void delRecursion(Integer id) {
        classifyDao.delClassfiy(id);

        // 要去判断 这个id分类 下面有没有分类了 有就删除了
        List<Classify> classifyListBySuperId = classifyDao.getClassifyListBySuperId(id);
        for (Classify classify : classifyListBySuperId) {
            delRecursion(classify.getId());
        }
    }

    @Override
    public FormSelectsDto getClassifyList(Integer goodsClassify) {
        // 获取分类表的 所有的信息
        List<Object> list = new ArrayList<>();
        List<Classify> classifyList = classifyDao.getClassifyListByHighest();
        for (Classify classify : classifyList) {
            LayuiLinkageDto layuiLinkageDto = new LayuiLinkageDto();
            List<Object> recursion = recursion(classify);

            layuiLinkageDto.setName(classify.getClassifyName());
            layuiLinkageDto.setValue(classify.getId());
            layuiLinkageDto.setChildren(recursion);
            list.add(layuiLinkageDto);
        }

        String msg = null;
        //goodsClassify == null 表示这个是要新增的页面  不用去回显标签
        if (goodsClassify != null) {
            Classify classify = classifyDao.selectClassifyById(goodsClassify);
            //封装成这样的数据['5/10']
            String strTag = "" + classify.getId();
            List<Object> strTagList = new ArrayList<>();
            strTag = classifyTagResive(classify, strTag);//回显商品 分类信息的递归

            strTagList.add(strTag);
            
            JSONArray fromObject =new JSONArray(strTagList);
            msg = fromObject.toString();
        }

        FormSelectsDto formSelectsDto = new FormSelectsDto();
        formSelectsDto.setCode(0);
        formSelectsDto.setMsg(msg);
        formSelectsDto.setData(list);
        return formSelectsDto;
    }

    //回显商品 分类信息的递归
    private String classifyTagResive(Classify classify, String strTag) {
        Classify classify2 = classifyDao.selectClassifyById(classify.getSuperId());

        if (classify2 != null) {
            strTag = classify.getSuperId() + "/" + strTag;
            classifyTagResive(classify2, strTag);
        }
        return strTag;
    }

    // 分类表的 递归
    private List<Object> recursion(Classify classify) {
        List<Object> list2 = new ArrayList<>();
        LayuiLinkageDto layuiLinkageDto = null;

        List<Classify> classifyListBySuperId = classifyDao.getClassifyListBySuperId(classify.getId());
        if (classifyListBySuperId.size() == 0) {
            return null;
        }
        for (Classify classify2 : classifyListBySuperId) {
            List<Object> recursion = recursion(classify2);

            layuiLinkageDto = new LayuiLinkageDto();

            layuiLinkageDto.setName(classify2.getClassifyName());
            layuiLinkageDto.setValue(classify2.getId());
            layuiLinkageDto.setChildren(recursion);
            list2.add(layuiLinkageDto);
        }
        return list2;
    }

    @Override
    @Cacheable(key = "targetClass.name+methodName+#goodsNumber")
    public FormSelectsDto getGoodsTag(String goodsNumber) {
        List<Object> list = new ArrayList<>();
        String msg = null;
        //goodsNumber == null 表示这个是要新增的页面  不用去回显标签
        if (goodsNumber != null) {
            //根据goodsNumber 去查询商品下面的所有的标签
            GoodsProperty goodsProperty = goodsPropertyDao.selectGoodsPropertyListByGoodsNumber(goodsNumber);
            List<String> goodsShowTag = GoodsTagUtils.getGoodsShowTag(goodsProperty.getContent());
            //封装成这样的数据['1/12/13', '1/12/14']
            String strTag = "";
            List<Object> strTagList = new ArrayList<>();
            for (String string : goodsShowTag) {
                PropertyValue propertyValue = propertyValueDao.getPropertyValueByValueId(Integer.parseInt(string));
                strTag = propertyValue.getPropertyId() + "/" + propertyValue.getValueId();
                strTagList.add(strTag);
            }
            
            JSONArray fromObject =new JSONArray(strTagList);
            msg = fromObject.toString();
        }

        FormSelectsDto formSelectsDto = new FormSelectsDto();
        formSelectsDto.setCode(0);
        formSelectsDto.setMsg(msg);
        formSelectsDto.setData(list);
        return formSelectsDto;
    }
    
   
    @Override
    @Cacheable(key = "targetClass.name+methodName")
    public treeGridDataDto<Classify> classifyInfo() {

        treeGridDataDto<Classify> treeGridDataDto = new treeGridDataDto<>();
        List<Classify> data = new ArrayList<>();
        treeGridDataDto.setCode(0);
        treeGridDataDto.setIs(true);
        treeGridDataDto.setMsg("");
        treeGridDataDto.setTip("操作成功！");

        List<Classify> classifyListByHighest = classifyDao.getClassifyListByHighest();
        for (Classify classify : classifyListByHighest) {
            data.add(classify);
        }
        data = getClassifyInfo(data, classifyListByHighest);

        Integer acount = classifyDao.getClassifyAcount();// 获取表的最大的id
        treeGridDataDto.setCount(acount);
        treeGridDataDto.setData(data);
        return treeGridDataDto;
    }

    // 查询 分类信息的递归id
    private List<Classify> getClassifyInfo(List<Classify> data, List<Classify> classifyListByHighest) {
        if (classifyListByHighest.size() == 0) {
            return data;
        }
        List<Classify> classifyListBySuperId = null;
        for (Classify classify : classifyListByHighest) {
            classifyListBySuperId = classifyDao.getClassifyListBySuperId(classify.getId());
            for (Classify classify2 : classifyListBySuperId) {
                data.add(classify2);
            }
        }
        return getClassifyInfo(data, classifyListBySuperId);
    }

}
