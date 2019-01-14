package com.bisa.health.shop.admin.controller;

import com.bisa.health.shop.admin.dto.SelectBean;
import com.bisa.health.shop.admin.service.IAdminGoodsImgService;
import com.bisa.health.shop.admin.util.JsonResult;
import com.bisa.health.shop.enumerate.IndexImgEnum;
import com.bisa.health.shop.enumerate.InternationalizationEnum;
import com.bisa.health.shop.model.GoodsImg;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.bisa.health.fastdfs.kit.FastDFSKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * bisa管理系统后台   主页
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/admin")
@RequiresRoles(value = {"ROLE_ADMIN", "ROLE_CUSTOMER", "ROLE_STORE"}, logical = Logical.OR)
public class AdminIndexController {

    @Autowired
    private IAdminGoodsImgService goodsImgService;

    /**
     * 进去 bisa 后台管理系统的主页
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "admin/adminIndex";
    }

    /**
     * 加载首页的图片
     * @param lang 语言版本
     * @return
     */
    @RequestMapping(value = "/indexImgs")
    @ResponseBody
    public List<GoodsImg> getIndexImgs(Integer lang) {
        List<GoodsImg> indexImgs = goodsImgService.getIndexImgsByLang(String.valueOf(IndexImgEnum.index_img.getValue()), lang);
        return indexImgs;
    }

    /**
     * 获取还没有国际化图片的版本
     */
    @RequestMapping(value = "/selectLanguage")
    @ResponseBody
    public List<SelectBean> addIndexImg() {

        List<SelectBean> list = new ArrayList<>();
        SelectBean bean;

        //下拉框用的国际化数据
        for (InternationalizationEnum e : InternationalizationEnum.values()) {
            bean = new SelectBean();
            bean.setKey(e.getValue());
            bean.setValue(e.getMsg());
            bean.setFlag(true);
            list.add(bean);
        }
        return list;
    }

    /**
     * 上传首页图片 (单张上传)
     * @param file                 接收图片的对象
     * @param internationalization 国际化语言
     * @param position             图片的位置
     */
    @RequestMapping(value = "/uploadIndexImg", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult uploadIndexImg( MultipartFile file,Integer internationalization, Integer position) {

        JsonResult jsonResult = null;

        //后台判断一下这个值，前台判断不了
        if (internationalization == null) {
            return new JsonResult("300", false, "语言版本为null，不符合要求");
        }

        String filename = UUID.randomUUID().toString() + ".jpg";
        try {
            // 上传图片到服务器返回一个图片的地址
            String url = FastDFSKit.getInstance().uploadPicToFastDFS(file.getBytes(), filename);
            // 上传图片的时候,根据语言和位置 去判断下远程有没有图片
            GoodsImg goodImg = goodsImgService.getImgByPosition(String.valueOf(IndexImgEnum.index_img.getValue()), position, internationalization);

            if (goodImg == null) {
                goodsImgService.addGoodsImg(new GoodsImg(null, String.valueOf(IndexImgEnum.index_img.getValue()), url, position, internationalization));
            } else {
                goodsImgService.updateGoodsImg(url, String.valueOf(IndexImgEnum.index_img.getValue()), position, internationalization);
                FastDFSKit.getInstance().deleteFromFastDfs(goodImg.getImgUrl());
            }
            jsonResult = new JsonResult("200", true);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = new JsonResult("500", false, "添加图片错误");
        }
        return jsonResult;
    }

}
