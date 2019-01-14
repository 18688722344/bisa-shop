package com.bisa.health.shop.controller;

import com.bisa.health.shop.dto.GoodsComboDto;
import com.bisa.health.shop.enumerate.GoodsClassify;
import com.bisa.health.shop.enumerate.GoodsStatusEnum;
import com.bisa.health.shop.model.*;
import com.bisa.health.shop.service.IClassifyService;
import com.bisa.health.shop.service.IComboService;
import com.bisa.health.shop.service.IGoodsComboService;
import com.bisa.health.shop.service.IGoodsImgService;
import com.bisa.health.shop.service.IGoodsService;
import com.bisa.health.shop.utils.InternationalizationUtil;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品控制器
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/web/call")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IGoodsImgService goodsImgService;
    @Autowired
    private IComboService comboService;
    @Autowired
    private IGoodsComboService goodsComboService;
    @Autowired
    private IClassifyService classifyService;

    /**
     * 心电仪 广告页面
     * /web/call/ecgAdvertisement
     * @return
     */
    @RequestMapping(value = "/ecgAdvertisement", method = RequestMethod.GET)
    public String ecgAdvertisement(String goodsNumber, Model model, HttpSession session) {
        //获取当前语言类型
        String lang = InternationalizationUtil.getLang(session);

        Goods goods = goodsService.selectGoodsByGoodsNumber(goodsNumber, lang);
        model.addAttribute("goods", goods);
        return "shopping/ecgAdvertisement";
    }

    /**
     * 商品详细页
     * @return
     */
    @RequestMapping(value = "/goodsDetail", method = RequestMethod.GET)
    public String productPage() {
        return "shopping/product";
    }

    /**
     * 根据商品的 (编号) 异步啦加载商品的基本信息
     * @param goodsNumber 商品的编号
     * @return
     */
    @RequestMapping(value = "/loadProductsJson", method = RequestMethod.GET)
    @ResponseBody
    public String loadProductsJson(String goodsNumber, HttpSession session) {

        //获取当前语言类型
        String lang = InternationalizationUtil.getLang(session);
        try {
            //获取 商品的所有图片
            List<GoodsImg> goodsImgs = goodsImgService.selectMainImgByGoodsNumber(goodsNumber, lang);

            //主商品信息
            Goods mainGoods = goodsService.selectGoodsByGoodsNumber(goodsNumber, lang);
            GoodsComboDto mainGoodsDto = new GoodsComboDto(mainGoods);



            //这里判断主商品的状态
            if (mainGoods.getStoreNumber() >= 1 && mainGoods.getGoodsStatus() == GoodsStatusEnum.in_sale.getValue()) {
                mainGoodsDto.setGoodsStatus(GoodsStatusEnum.in_sale.getValue());
            } else if (mainGoods.getStoreNumber() == 0 && mainGoods.getGoodsStatus() == GoodsStatusEnum.sale_out.getValue()) {
                mainGoodsDto.setGoodsStatus(GoodsStatusEnum.sale_out.getValue());
            } else if (mainGoods.getGoodsStatus() == GoodsStatusEnum.invalid.getValue()) {
                mainGoodsDto.setGoodsStatus(GoodsStatusEnum.invalid.getValue());
            }

            //判断主商品是否为虚拟商品，用于更改显示的样式。
            boolean isVirtual = false;
            Classify classify = classifyService.selectClassifyById(mainGoods.getClassifyId());
            if (classify != null && classify.getSuperId().equals(GoodsClassify.VIRTUAL.getValue())) {
                isVirtual = true;
            }

            List<Object> listGoodsCombo = new ArrayList<>();
            List<GoodsComboDto> listRelativeGoods = new ArrayList();

            //获取该商品下面的，所有子级商品
            List<Goods> listGoods = goodsService.listChildrenGoodsByParentId2(mainGoods.getId(), lang);

            for (Goods Goods : listGoods) {
                GoodsComboDto goodsComboDto = new GoodsComboDto(Goods);
                //这里判断商品的状态
                if (Goods.getStoreNumber() >= 1 && Goods.getGoodsStatus() == GoodsStatusEnum.in_sale.getValue()) {
                    goodsComboDto.setGoodsStatus(GoodsStatusEnum.in_sale.getValue());
                } else if (Goods.getStoreNumber() == 0 && Goods.getGoodsStatus() == GoodsStatusEnum.sale_out.getValue()) {
                    goodsComboDto.setGoodsStatus(GoodsStatusEnum.sale_out.getValue());
                } else if (Goods.getGoodsStatus() == GoodsStatusEnum.invalid.getValue()) {
                    goodsComboDto.setGoodsStatus(GoodsStatusEnum.invalid.getValue());
                }
                listRelativeGoods.add(goodsComboDto);
            }

            if (isVirtual) {
                //虚拟商品，获取子级商品（服务激活卡）
                listGoodsCombo.addAll(listRelativeGoods); //套餐项为本商品的所有子级商品（服务激活卡）
                //相关商品为父级商品的所有子级商品（如心电仪）
                listRelativeGoods = goodsService.listChildrenGoodsByParentId(mainGoods.getParentId(), lang);
            } else {
                //实体商品，获取所有套餐
                List<GoodsCombo> goodsComboList = goodsComboService.getGoodsComboByGoodsNumber(goodsNumber);

                for (GoodsCombo goodsCombo : goodsComboList) {
                    //获取套餐集合
                    List<GoodsComboDto> goodsComboDtoList = new ArrayList();
                    List<Combo> comboList = comboService.selectComboByGuid(goodsCombo.getComboGuid());

                    for (Combo combo : comboList) {
                        GoodsComboDto goodsComboDto = new GoodsComboDto(combo);
                        Goods goods = goodsService.selectGoodsByGoodsNumber(combo.getGoodsNumber(), lang);
                        //这里判断主商品的状态
                        if (goods.getStoreNumber() >= combo.getCount() && goods.getGoodsStatus() == GoodsStatusEnum.in_sale.getValue()) {
                            goodsComboDto.setGoodsStatus(GoodsStatusEnum.in_sale.getValue());
                        } else if (goods.getStoreNumber() < combo.getCount() && goods.getGoodsStatus() == GoodsStatusEnum.sale_out.getValue()) {
                            goodsComboDto.setGoodsStatus(GoodsStatusEnum.sale_out.getValue());
                        } else if (goods.getGoodsStatus() == GoodsStatusEnum.invalid.getValue()) {
                            goodsComboDto.setGoodsStatus(GoodsStatusEnum.invalid.getValue());
                        }
                        goodsComboDtoList.add(goodsComboDto);
                    }
                    if (goodsComboDtoList != null) {
                        listGoodsCombo.add(goodsComboDtoList);
                    }
                }
            }

            //主商品获取配件商品
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("mainProduct", mainGoods); //主商品的信息和主图对象
            jsonObject.put("mainProductDto", mainGoodsDto); //用于主商品立即购买
            jsonObject.put("isVirtual", isVirtual); //是否虚拟商品
            jsonObject.put("goodsImgs", goodsImgs); //主商品的所有图片对象
            jsonObject.put("packages", listGoodsCombo); //套餐数据
            jsonObject.put("relative", listRelativeGoods); //相关产品

            // 返回前台一个 jsonString类型的数据结构
            String jsonstr = jsonObject.toString();
            return jsonstr;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
