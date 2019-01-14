package com.bisa.health.shop.admin.service;

import com.bisa.health.shop.admin.dao.IAdminGoodsPropertyDao;
import com.bisa.health.shop.admin.model.GoodsProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminGoodsPropertyServiceImpl implements IAdminGoodsPropertyService {

    @Autowired
    private IAdminGoodsPropertyDao goodsPropertyDao;

    @Override
    public void addGoodsProperty(GoodsProperty goodsProperty) {
        goodsPropertyDao.addGoodsProperty(goodsProperty);
    }

    @Override
    public void updateGoodsProperty(GoodsProperty goodsProperty) {
        goodsPropertyDao.updateGoodsProperty(goodsProperty);
    }

}
