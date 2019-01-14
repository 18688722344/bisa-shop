package com.bisa.health.shop.admin.controller;

import com.bisa.health.app.model.ServiceCategory;
import com.bisa.health.common.enumerate.ExceptionCodeEnum;
import com.bisa.health.common.utils.RandomUtils;
import com.bisa.health.common.web.service.IServiceCategoryService;
import com.bisa.health.shop.admin.dto.AddComboDto;
import com.bisa.health.shop.admin.dto.FormSelectsDto;
import com.bisa.health.shop.admin.dto.OrderListPageDto;
import com.bisa.health.shop.admin.dto.SelectBean;
import com.bisa.health.shop.admin.service.*;
import com.bisa.health.shop.admin.util.GoodsTagUtils;
import com.bisa.health.shop.admin.util.JsonResult;
import com.bisa.health.shop.admin.util.OrderNoUtils;
import com.bisa.health.shop.enumerate.GoodsClassify;
import com.bisa.health.shop.enumerate.GoodsStatusEnum;
import com.bisa.health.shop.enumerate.InternationalizationEnum;
import com.bisa.health.shop.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.bisa.health.fastdfs.kit.FastDFSKit;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 商品管理-restful接口控制器
 * @author Administrator
 */

@RequestMapping(value = "/admin/commodity")
@RestController
@RequiresRoles(value = {"ROLE_ADMIN", "ROLE_CUSTOMER"}, logical = Logical.OR)
public class AdminCommodityRestController {

    @Autowired
    private IAdminGoodsService goodsService;
    @Autowired
    private IAdminGoodsImgService goodsImgService;
    @Autowired
    private IAdminClassifyService classifyService;
    @Autowired
    private IAdminComboService comboService;
    @Autowired
    private IAdminGoodsComboService goodsComboService;
    @Autowired
    private IAdminServiceCardRecordService serviceCardRecordService;
    @Autowired
    private IServiceCategoryService serviceCategoryService;
    @Autowired
    private IAdminGoodsInternationalizationService goodsInternationalizationService;

    private Logger logger = LogManager.getLogger(AdminCommodityRestController.class);

    /**
     * 获取指定编号的商品数据
     */
    @RequestMapping(value = "/loadGoodsInfo", method = RequestMethod.GET)
    public Goods loadGoodsInfo( String goodsNumber) {
        return goodsService.selectGoodsByGoodsNumber(goodsNumber);
    }

    /**
     * 新增、编辑商品页面加载数据
     */
    @RequestMapping(value = "/loadMainGoodsObject", method = RequestMethod.GET)
    public JSONObject loadMainGoodsObject(@RequestParam(required = false) String goodsNumber) {

        List<Goods> list = goodsService.loadMainGoodsObject();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", list);

        if (!StringUtils.isEmpty(goodsNumber)) {
            Goods goods = goodsService.selectGoodsByGoodsNumber(goodsNumber);
            FormSelectsDto formSelectsDto = classifyService.getClassifyList(goods.getClassifyId());
            jsonObject.put("goods", goods);
            jsonObject.put("formSelectsDto", formSelectsDto.getMsg());
        }
        return jsonObject;
    }

    /**
     * 商品信息 国际化
     * @param goods_number         商品编号
     * @param internationalization 国际化语言类型
     * @return
     */
    @RequestMapping(value = "/loadGoodsInternationalization", method = RequestMethod.GET)
    public JSONObject loadGoodsInternationalization(String goods_number, int internationalization) {

        JSONObject jsonObject = new JSONObject();
        GoodsInternationalization goodsInternationalization = goodsInternationalizationService.selectInsternationalzationByInternationalization(goods_number, internationalization);

        jsonObject.put("list", goodsInternationalization);
        return jsonObject;
    }

    /**
     * 添加商品 国际化语言
     * @param goodsNumber 商品编号
     * @param description 商品描述
     * @param goodsName   商品名字
     * @param title       商品标题
     * @return
     */
    @RequestMapping(value = "/insertGoodsInsternationalzation", method = RequestMethod.POST)
    public JsonResult insertGoodsInsternationalzation(String goodsNumber, String description, String goodsName, String title, int internationalization) {

        JsonResult jsonResult = new JsonResult();
        Goods goods = goodsService.selectGoodsByGoodsNumber(goodsNumber);

        try {
            //获取该商品的语言版本
            GoodsInternationalization goodsInternationalization = goodsInternationalizationService.selectInsternationalzationByInternationalization(goodsNumber, internationalization);

            if (goodsInternationalization == null) {
                // 新增操作(图片是没有的现在，等保存好了之后，再去上传图片)
                GoodsInternationalization goodsInter = new GoodsInternationalization();
                goodsInter.setGoods_id(goods.getId());
                goodsInter.setInternationalization(internationalization);
                goodsInter.setGoods_name(goodsName);
                goodsInter.setTitle(title);
                goodsInter.setDescription(description);
                goodsInter.setGoods_number(goodsNumber);

                goodsInternationalizationService.insertGoodsInsternationalzation(goodsInter);
                jsonResult.setFlag(true);
            } else {
                jsonResult.setMsg("300");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setFlag(false);
            logger.error(e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 添加语音版本页面，获取还没有国际化语言的版本
     * @param goodsNumber 商品的编号
     * @return
     */
    @RequestMapping(value = "/findLanguage", method = RequestMethod.GET)
    public JSONObject findLanguage(String goodsNumber) {


        List<SelectBean> list = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        try {
            SelectBean bean;
            for (InternationalizationEnum e : InternationalizationEnum.values()) {

                bean = new SelectBean();
                //这里判断一下 国际化语言 是否存在
                GoodsInternationalization goodsInternationalization = goodsInternationalizationService.selectInsternationalzationByInternationalization(goodsNumber, e.getValue());

                //有这个版本的语言就跳过去
                if (goodsInternationalization != null) {
                    continue;
                }

                bean.setKey(e.getValue());
                bean.setValue(e.getMsg());
                list.add(bean);
                jsonObject.put("list", list);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return jsonObject;
    }

    /**
     * 添加语音版本页面，获取还没有国际化语言的版本
     * @return
     */
    @RequestMapping(value = "/newsFindLanguage", method = RequestMethod.GET)
    public JSONObject newsFindLanguage() {

        List<SelectBean> list = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        try {
            SelectBean bean;
            for (InternationalizationEnum e : InternationalizationEnum.values()) {

                bean = new SelectBean();
                bean.setKey(e.getValue());
                bean.setValue(e.getMsg());
                list.add(bean);
                jsonObject.put("list", list);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return jsonObject;
    }

    /**
     * 新增  商品信息
     * @param goods       商品接收对象
     * @param classifyId: 5/10  layui格式分类数据
     * @param goodsTag:   1/1000  layui格式商品标签数据
     */
    @RequestMapping(value = "/insertCommodity", method = RequestMethod.POST)
    public JsonResult insertCommodity(Goods goods, @RequestParam(required = false) String goodsTag, String classifyId) {

        JsonResult jsonResult = new JsonResult();
        GoodsInternationalization goodsInternationalization = new GoodsInternationalization();
        String goodsNumber = null;
        try {
            // 新增操作
            String classifyStr = GoodsTagUtils.getGoodsTag(classifyId);
            int classify_id = Integer.parseInt(classifyStr);
            goodsNumber = OrderNoUtils.getOrderIdByTime();
            goods.setGoodsNumber(goodsNumber);
            goods.setPutawayTime(new Date());
            goods.setClassifyId(classify_id);
            goods.setSalesNumber(0);
            goods.setAppraiseNumber(0);
            //虚拟商品，与父级商品共用图片
            if (classify_id == GoodsClassify.COUNT.getValue() || classify_id == GoodsClassify.TIME.getValue()) {
                Goods oldGoods = goodsService.getGoodsById(goods.getParentId());
                if (oldGoods.getImgUrl() != null) {
                    goods.setImgUrl(oldGoods.getImgUrl());
                    goodsInternationalization.setImg_url(oldGoods.getImgUrl());
                    List<GoodsImg> list = goodsImgService.selectMainImgByGoodsNumber(oldGoods.getGoodsNumber());
                    for (GoodsImg goodsImg : list) {
                        goodsImg.setGoodsNumber(goodsNumber);
                        goodsImgService.addGoodsImg(goodsImg);
                    }
                }
            }

            jsonResult.setMsg(goodsNumber); //用于添加图片
            // 新增商品
            goodsService.addGoods(goods);
            //获取商品的ID用于关联商品国际化
            Goods goods1 = goodsService.selectGoodsByGoodsNumber(goodsNumber);
            //新增到商品国际化表
            goodsInternationalization.setGoods_id(goods1.getId());
            goodsInternationalization.setInternationalization(2);//默认添加是繁体
            goodsInternationalization.setGoods_number(goodsNumber);
            goodsInternationalization.setGoods_name(goods.getGoodsName());
            goodsInternationalization.setTitle(goods.getTitle());
            goodsInternationalization.setDescription(goods.getDescription());
            goodsInternationalizationService.insertGoodsInsternationalzation(goodsInternationalization);
            //商品标签，暂未启用
				/*
				GoodsProperty goodsProperty = new GoodsProperty();
				goodsProperty.setGoods_number(goodsNumber);
				goodsProperty.setContent(GoodsTagUtils.getGoodsTag(goodsTag));
				goodsPropertyService.addGoodsProperty(goodsProperty);
				 */
            jsonResult.setFlag(true);
            return jsonResult;

        } catch (Exception e) {
            e.printStackTrace();
        }

        jsonResult.setFlag(false);
        return jsonResult;
    }

    /**
     * 编辑  商品信息
     * @param goods       商品接收对象
     * @param classifyId: 5/10  layui格式分类数据
     * @param goodsTag:   1/1000  layui格式商品标签数据
     */
    @RequestMapping(value = "/updateCommodity", method = RequestMethod.POST)
    public JsonResult updateCommodity( Goods goods,String goodsTag,String classifyId) {

        JsonResult jsonResult = new JsonResult();
        GoodsInternationalization goodsInternationalization=new GoodsInternationalization();
        try {
            // 更新操作
            if (goods.getGoodsNumber() != null) {
                // 判断有没有点下架的按钮
                if (goods.getGoodsStatus() == GoodsStatusEnum.invalid.getValue()) {
                    goods.setSoldoutTime(new Date());
                }
                String classifyStr = GoodsTagUtils.getGoodsTag(classifyId);
                goods.setClassifyId(Integer.parseInt(classifyStr));
                //更新国际化商品表
                goodsInternationalization.setInternationalization(2);
                goodsInternationalization.setGoods_number(goods.getGoodsNumber());
                goodsInternationalization.setGoods_name(goods.getGoodsName());
                goodsInternationalization.setTitle(goods.getTitle());
                goodsInternationalization.setDescription(goods.getDescription());

                // 更新商品的时候要去 判断商品的单价有没有变化 变化了就去更新 套餐中商品的价格
                Goods oldGoods = goodsService.selectGoodsByGoodsNumber(goods.getGoodsNumber());
                if (oldGoods.getGoodsPrice() != goods.getGoodsPrice()) {
                    comboService.updateGoodsPriceByGoodsNumber(goods);
                }

                // 更新商品 和 更新商品标签表
                goodsService.updateGoods(goods);
                goodsInternationalizationService.updateInternational(goodsInternationalization);
                //商品标签，暂未启用
				/*
				GoodsProperty goodsProperty = new GoodsProperty();
				goodsProperty.setGoods_number(goods.getGoodsNumber());
				goodsProperty.setContent(GoodsTagUtils.getGoodsTag(goodsTag));
				goodsPropertyService.updateGoodsProperty(goodsProperty);
				 */
                // 这个表示更新的是 主商品
                if (goods.getParentId() == 0) {
                    jsonResult.setMsg("100");
                } else {
                    jsonResult.setMsg("200");
                }
            }

            jsonResult.setFlag(true);
        } catch (Exception e) {
            jsonResult.setFlag(false);
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 编辑商品国际化语言版本 商品信息
     * @param internationalization
     * @param goodsNumber
     * @param description
     * @param goodsName
     * @param title
     * @return
     */
    @RequestMapping(value = "/updateInternational", method = RequestMethod.POST)
    public JsonResult updateInternational(int internationalization, String goodsNumber, String description, String goodsName, String title) {

        JsonResult jsonResult = new JsonResult();
        GoodsInternationalization goodsInternationalization = new GoodsInternationalization();

        try {
            if (description != "" || goodsName != "" || title != "") {
                goodsInternationalization.setTitle(title);
                goodsInternationalization.setGoods_name(goodsName);
                goodsInternationalization.setGoods_number(goodsNumber);
                goodsInternationalization.setInternationalization(internationalization);
                goodsInternationalization.setDescription(description);

                goodsInternationalizationService.updateInternational(goodsInternationalization);
                jsonResult.setFlag(true);
            } else {
                jsonResult.setFlag(false);
            }

        } catch (Exception e) {
            jsonResult.setFlag(false);
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 加载商品类别数据，如"3/10"
     * @param goodsClassify 回显商品分类的信息要的id
     * @return
     */
    @RequestMapping(value = "/getClassifyInfo", method = RequestMethod.GET)
    public FormSelectsDto getClassifyInfo( Integer goodsClassify) {
        FormSelectsDto dto = classifyService.getClassifyList(goodsClassify);
        return dto;
    }

    /**
     * 加载商品标签数据
     * @param goodsNumber 商品编号
     * @return
     */
    @RequestMapping(value = "/getGoodsTag", method = RequestMethod.GET)
    public FormSelectsDto getGoodsTag( String goodsNumber) {
        FormSelectsDto dto = classifyService.getGoodsTag(goodsNumber);
        return dto;
    }

    /**
     * 加载商品图片
     * @param goodsNumber          商品编号
     * @param internationalization 商品国际化语言
     */
    @RequestMapping(value = "/loadGoodsImgInfo", method = RequestMethod.GET)
    public List<GoodsImg> loadGoodsImgInfo( String goodsNumber,Integer internationalization) {

        //这里的internationalization 语言版本，要判断下，更新商品的时候第一次查询图片，internationalization是为null
        if (internationalization == null) {
            internationalization = InternationalizationEnum.zh_HK.getValue();
        }
        List<GoodsImg> list = goodsImgService.selectGoodsImgs(goodsNumber, internationalization);
        return list;
    }

    /**
     * 上传商品图片 (单张上传)  图片不要裁剪，以免影响分辨率
     * @param file                 接收图片的对象
     * @param internationalization 国际化语言
     * @param goodsNumber          商品的编号
     * @param position             图片的位置
     * @return
     */
    @RequestMapping(value = "/uploadPicture", method = RequestMethod.POST)
    public JsonResult uploadPicture( MultipartFile file, String goodsNumber, Integer internationalization, Integer position) {

        JsonResult jsonResult = new JsonResult();
        String filename = UUID.randomUUID().toString() + ".jpg";
        try {
            // 上传图片到服务器返回一个图片的地址
            String url = FastDFSKit.getInstance().uploadPicToFastDFS(file.getBytes(), filename);
            // 上传图片的时候去判断下远程有没有图片
            GoodsImg goodsImg = goodsImgService.getImgByPosition(goodsNumber, position, internationalization);

            // 没有对象就是第一次上传 图片的操作
            if (goodsImg == null) {
                goodsImgService.addGoodsImg(new GoodsImg(null, goodsNumber, url, position, internationalization));
            } else {
                // 更新商品的图片表
                goodsImgService.updateGoodsImg(url, goodsNumber, position, internationalization);
                // 主图片保留,其他图片删除了
                if (position != 1) {
                    FastDFSKit.getInstance().deleteFromFastDfs(goodsImg.getImgUrl());
                }
            }

            //图片是主图的时候
            if (position == 1) {
                //更新商品国际化语言表图片
                goodsInternationalizationService.updateImgUrl(url, goodsNumber, internationalization);
                //更新商品表图片
                goodsService.updateGoodsImg(url, goodsNumber);
                //更新套餐表图片
                comboService.updateImg(url, goodsNumber);
            }

            jsonResult.setMsg(url);
            jsonResult.setFlag(true);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setFlag(false);
        }
        return jsonResult;
    }

    /**
     * 商品分页数据
     * @param page  第几页
     * @param limit 每页有  多少数据
     * @return
     */
    @RequestMapping(value = "/adminCommodityRest", method = RequestMethod.GET)
    public OrderListPageDto<Goods> adminCommodityRest(Integer page, Integer limit, HttpServletRequest request) {

        String incontent = request.getParameter("key[incontent]");
        String searchabout = request.getParameter("key[searchabout]");
        String val = request.getParameter("key[val]");
        OrderListPageDto<Goods> listPageDto = goodsService.selectAllGoods(page, limit, incontent, searchabout, val);
        return listPageDto;
    }

    /**
     * 加载指定商品所有图片
     * @param goodsNumber 商品的编号
     * @return
     */
    @RequestMapping(value = "/getGoodsImgList", method = RequestMethod.GET)
    public List<GoodsImg> getGoodsImgList( String goodsNumber, int internationalization) {
        List<GoodsImg> list = goodsImgService.selectImgByInternationalization(goodsNumber,internationalization);
        return list;
    }

    /**
     * 获得指定商品数据
     * @param goodsNumber 商品的编号
     * @return
     */
    @RequestMapping(value = "/getGoodsObject", method = RequestMethod.GET)
    public Goods getGoodsObject( String goodsNumber) {
        Goods goods = goodsService.selectGoodsByGoodsNumber(goodsNumber);
        return goods;
    }

    /**
     * 加载商品详情（带图片）
     * @return
     */
    @RequestMapping(value = "/getGoodsDetail", method = RequestMethod.GET)
    public JSONObject getGoodsDetail(HttpSession session) {

        String goodsNumber = (String) session.getAttribute("goodsNumber");
        session.removeAttribute("goodsNumber");

        Goods goods = goodsService.selectGoodsByGoodsNumber(goodsNumber);
        List<GoodsImg> GoodsImgList = goodsImgService.selectMainImgByGoodsNumber(goodsNumber);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("goods", goods);
        jsonObject.put("GoodsImgList", GoodsImgList);
        return jsonObject;
    }

    /**
     * 加载套餐列表数据
     * @param goodsNumber 商品的编号
     * @return
     */
    @RequestMapping(value = "/listGoodsCombo", method = RequestMethod.GET)
    public OrderListPageDto<Combo> listGoodsCombo( String goodsNumber) {

        List<Combo> listObject = new ArrayList<Combo>();
        List<GoodsCombo> list = goodsComboService.getGoodsComboGuidByGoodsNumber(goodsNumber);
        for (GoodsCombo goodsCombo : list) {
            List<Combo> comboList = comboService.getComboByComboGuid(goodsCombo.getComboGuid());
            listObject.addAll(comboList);
        }

        // 封装layui要的数据结构
        OrderListPageDto<Combo> listPageDto = new OrderListPageDto<>();
        listPageDto.setCode(0);
        listPageDto.setMsg("''");
        listPageDto.setCount((long) listObject.size());
        listPageDto.setData(listObject);
        return listPageDto;
    }

    /**
     * 根据套餐的编号，获取套餐的对象
     * @return
     */
    @RequestMapping(value = "/getCombosObject", method = RequestMethod.GET)
    public Combo getCombosObject(int id) {
        return comboService.selectCombosByComboId(id);
    }

    /**
     * 删除套餐商品
     * @return
     */
    @RequestMapping(value = "delComboById", method = RequestMethod.POST)
    public String delComboById(String comboGuid) {
        try {
            boolean result = goodsComboService.delGoodsCombo(comboGuid);    //删除商品套餐关系表
            result &= comboService.delCombo(comboGuid);        //删除该套餐所有详情
            if (result) {
                return ExceptionCodeEnum.SUCCESS.getValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ExceptionCodeEnum.FAILURE.getValue();
    }

    /**
     * 删除商品
     * @param goods_number
     * @return
     */
    @RequestMapping(value = "/delectGoods",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult delectGoods(String goods_number){
            JsonResult jsonResult=new JsonResult();
        try {
            List<Combo> comboList = comboService.selectGoodsList(goods_number);//查询商品相关的套餐
            if (comboList!=null){
                for (Combo combo: comboList){
                    goodsComboService.delGoodsCombo(combo.getComboGuid());    //删除商品套餐关系表
                    comboService.delCombo(combo.getComboGuid());//删除该套餐所有详情
                }

            }
            boolean deleteGoodsInternationByGoosNumber=goodsInternationalizationService.deleteGoodsInternationByGoosNumber(goods_number);
            boolean deleteGoodsByGoosNumber=goodsInternationalizationService.deleteGoodsByGoosNumber(goods_number);
            goodsImgService.delImg(goods_number);
            if (deleteGoodsInternationByGoosNumber==true && deleteGoodsByGoosNumber==true){
                jsonResult.setFlag(true);
            }else{
                jsonResult.setFlag(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
    /**
     * 套餐页面 加载商品的所有的信息
     * @return
     */
    @RequestMapping(value = "/loadGoodsList", method = RequestMethod.GET)
    public List<Goods> loadGoodsList() {
        List<Goods> list = goodsService.loadGoodsList();

        return list;
    }

    /**
     * 更改编辑套餐价格
     * @param id
     * @return
     */
    @RequestMapping(value = "/updataCombo", method = RequestMethod.POST)
    public String updataCombo(float discount_price, int id) {
        try {
            boolean result = comboService.updataCombo(discount_price, id);
            if (result) {
                return ExceptionCodeEnum.SUCCESS.getValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ExceptionCodeEnum.FAILURE.getValue();
    }

    /**
     * 添加套餐
     * @return
     */
    @RequestMapping(value = "/insertCombo", method = RequestMethod.POST)
    public JsonResult insertCombo( AddComboDto comboDto,String goodsNumber) {
        JsonResult jsonResult = new JsonResult();

        String valueId = RandomUtils.randomSixNum();// 这个作为combo的guid
        try {
            // 主商品
            Goods mainGoods = goodsService.selectGoodsByGoodsNumber(comboDto.getMainGoodsNumber());
            // 添加商品套餐关系
            GoodsCombo goodsCombo = new GoodsCombo();
            goodsCombo.setComboGuid(valueId);
            goodsCombo.setGoodsNumber(mainGoods.getGoodsNumber());
            goodsComboService.insertGoodsCombo(goodsCombo);

            // 添加主商品套餐详情
            Combo combo = new Combo();
            combo.setComboGuid(valueId);
            combo.setClassifyId(mainGoods.getClassifyId());
            combo.setGoodsNumber(mainGoods.getGoodsNumber());
            combo.setGoodsName(mainGoods.getGoodsName());
            combo.setCount(comboDto.getMainGoodsCount());
            combo.setGoodsPrice(mainGoods.getGoodsPrice());
            combo.setDiscountPrice(comboDto.getMainGoodsPrice());
            combo.setImgUrl(mainGoods.getImgUrl());
            combo.setNeedPost(mainGoods.getNeedPost());

            // 新增goodsCombo 表
            comboService.insertCombo(combo);

            // 配套商品-套餐
            if (!StringUtils.isEmpty(comboDto.getAssistantGoodsNumber())) {
                Goods assistantGoods = goodsService.selectGoodsByGoodsNumber(comboDto.getAssistantGoodsNumber());

                combo = new Combo();
                combo.setComboGuid(valueId);
                combo.setClassifyId(assistantGoods.getClassifyId());
                combo.setGoodsNumber(assistantGoods.getGoodsNumber());
                combo.setGoodsName(assistantGoods.getGoodsName());
                combo.setCount(comboDto.getAssistantGoodsCount());
                combo.setGoodsPrice(assistantGoods.getGoodsPrice());
                combo.setDiscountPrice(comboDto.getAssistantGoodsPrice());
                combo.setImgUrl(assistantGoods.getImgUrl());
                combo.setNeedPost(assistantGoods.getNeedPost());
                // 新增combo
                comboService.insertCombo(combo);
            }

            jsonResult.setFlag(true);
        } catch (Exception e) {
            jsonResult.setFlag(false);
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 查询所有激活卡信息
     * @param page  第几页
     * @param limit 每页有多少数据
     * @return
     */
    @RequestMapping(value = "/loadServiceCardList", method = RequestMethod.GET)
    public OrderListPageDto<ServiceCard> loadServiceCardList( Integer page, Integer limit,
                                                             HttpServletRequest request) {

        String incontent = request.getParameter("key[incontent]");
        String searchabout = request.getParameter("key[searchabout]");
        String val = request.getParameter("key[val]");
        OrderListPageDto<ServiceCard> listPageDto = serviceCardRecordService.loadServiceCardList(page, limit, incontent, searchabout, val);
        return listPageDto;
    }

    /**
     * 加载服务编号数据
     * 异步加载  服务卡类型
     * @return
     */
    @RequestMapping(value = "/loadServiceCategoryObject", method = RequestMethod.GET)
    public List<ServiceCategory> loadServiceCategoryObject() {
        List<ServiceCategory> listServiceCategory = serviceCategoryService.listServiceCategory();
        return listServiceCategory;
    }

    /**
     * 加载所有虚拟商品
     * @return
     */
    @RequestMapping(value = "/listVirtualGoods", method = RequestMethod.GET)
    public List<Goods> listVirtualGoods() {
        List<Goods> list = new ArrayList<Goods>();
        List<Goods> listCount = goodsService.selectGoodsByClassifyId(GoodsClassify.COUNT.getValue());
        List<Goods> listTiming = goodsService.selectGoodsByClassifyId(GoodsClassify.TIME.getValue());
        list.addAll(listCount);
        list.addAll(listTiming);
        return list;
    }

    /**
     * 批量生成激活卡
     * @return
     */
    @RequestMapping(value = "/createServiceCard", method = RequestMethod.POST)
    public JsonResult createServiceCard(ServiceCard serviceCard, int amount) {

        int creatAmount = 0;
        JsonResult jsonResult = new JsonResult();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            for (int i = 0; i < amount; i++) {
                String cardNumber = "BISA" + sdf.format(new Date()) + String.valueOf((10000 + i)) + "CN";
                String activeCode = RandomUtils.randomEightNum();
                ServiceCard card = new ServiceCard();
                card.setCardName(serviceCard.getCardName());
                card.setCardNumber(cardNumber);
                card.setActiveCode(activeCode);
                card.setGoodsNumber(serviceCard.getGoodsNumber());
                card.setCardType(serviceCard.getCardType());
                card.setServiceToken(serviceCard.getServiceToken());
                card.setCount(serviceCard.getCount());
                card.setCardStatus(0);
                boolean result = serviceCardRecordService.addServiceCard(card);
                if (result) {
                    creatAmount += 1;
                }
            }

            logger.debug("Created " + creatAmount + " service cards！");

            //修改商品表激活卡数量
            Goods goods = goodsService.selectGoodsByGoodsNumber(serviceCard.getGoodsNumber());
            goods.setStoreNumber(goods.getStoreNumber() + creatAmount);
            goodsService.updateGoods(goods);

            logger.debug("Updated goods store number:" + goods.getStoreNumber() + creatAmount);

            jsonResult.setMsg(String.valueOf(creatAmount));
            jsonResult.setFlag(true);
            return jsonResult;
        } catch (Exception e) {
            e.printStackTrace();
        }

        jsonResult.setMsg(String.valueOf(creatAmount));
        jsonResult.setFlag(false);
        return jsonResult;
    }

}


