package com.bisa.health.shop.controller;

import com.bisa.health.app.enumerate.RedisKey;
import com.bisa.health.shiro.web.bind.CurrentUser;
import com.bisa.health.client.entity.User;
import com.bisa.health.client.remote.RemoteInterface;
import com.bisa.health.cache.kit.RedisKit;
import com.bisa.health.common.app.service.ISmsService;
import com.bisa.health.common.enumerate.ExceptionCodeEnum;
import com.bisa.health.common.utils.RandomUtils;
import com.bisa.health.pay.enumerate.PayStatus;
import com.bisa.health.shop.model.Order;
import com.bisa.health.shop.model.ServiceCardRecord;
import com.bisa.health.shop.service.IOrderService;
import com.bisa.health.shop.service.IServiceCardRecordService;
import com.bisa.health.shop.utils.Base64ImgUtils;
import com.bisa.health.shop.validator.ValidWebBean;
import com.bisa.health.shop.validator.ValidWebBean.CHANGE_PWD_EMAIL_GROUP;
import com.bisa.health.shop.validator.ValidWebBean.CHANGE_PWD_SMS_GROUP;
import com.bisa.health.shop.validator.ValidWebBean.EMAIL_GROUP;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bisa.health.fastdfs.kit.FastDFSKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * 个人中心
 * @author Administrator
 */

@Controller
@RequestMapping("/user")
public class UserCenterController {

    @Value("${default.headimg}")
    private String default_headimg;

    @Autowired
    private ISmsService smsService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IServiceCardRecordService serviceCardRecordService;
    @Autowired
    private RedisKit redisKit;


    /**
     * 短信缓存时间
     */
    @Value("${expiration.sms}")
    private String SMS_SECONDS;

    /**
     * 邮箱验证码时效
     */
    @Value("${expiration.mail}")
    private String EMAIL_SECONDS;

    private Logger logger = LogManager.getLogger(UserCenterController.class);
    
    @Autowired
    private RemoteInterface remoteService;
    /**
     * 个人中心
     * @return
     */
    @RequestMapping(value = "/userCenter", method = RequestMethod.GET)
    public String userCenter(@CurrentUser User sysUser) {
    	remoteService.updateUser(sysUser);
        return "user-center/userCenter";
    }

    /**
     * 个人中心加载数据
     * @return
     */
    @RequestMapping(value = "/userCenterRest", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject userCenterRest(@CurrentUser User sysUser) {

        //用户的头像，非缓存的方式获取的
        String img_pic_path =sysUser.getUriPic();
        if (StringUtils.isEmpty(img_pic_path)) {
            img_pic_path = default_headimg;
        }

        // 查询待支付，待收货(有2个状态 发货和未发货的情况)，待评价，待激活服务数量
        int payCount = 0;
        int deliverycount = 0;
        int appraiseCount = 0;
        int activeCount = 0;
        //统计订单数据
        List<Order> userOrderList = orderService.getListOrderByUserGuid(sysUser.getUser_guid());
        for (Order order : userOrderList) {
            if (order.getTra_status() == PayStatus.unpaid.getValue()) {
                payCount += 1;
            } else if (order.getTra_status() == PayStatus.waitDelivery.getValue() || order.getTra_status() == PayStatus.paid.getValue()) {
                deliverycount += 1;
            } else if (order.getTra_status() == PayStatus.received.getValue()) {
                appraiseCount += 1;
            }
        }

        //查询待激活服务数量
        List<ServiceCardRecord> serviceCardRecordList = serviceCardRecordService.getListServiceCardRecordByGuidAndStatus(sysUser.getUser_guid(), 0);
        activeCount = serviceCardRecordList.size();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("img_pic", img_pic_path);
        jsonObject.put("payCount", payCount);
        jsonObject.put("deliveryCount", deliverycount);
        jsonObject.put("appraiseCount", appraiseCount);
        jsonObject.put("activeCount", activeCount);
        return jsonObject;
    }

    /**
     * 添加头像
     * @param img_portrait 剪裁过的图片64位编码
     * @return
     */
    @RequestMapping(value = "/uploadPortrait", method = RequestMethod.POST)
    @ResponseBody
    public String upload_portrait(@CurrentUser User sysUser,String img_portrait) {

        if (img_portrait == null || StringUtils.isEmpty(img_portrait)) {
            // 图片不能为空
            return ExceptionCodeEnum.NULL_PARAM.getValue();
        }
        try {
            // 上传图片到fastdfs服务器，返回图片url
            String picUrl = uploadPic(img_portrait);
            if (picUrl != null) {
                //这里表示上传了图片，判断下原来有没有图片
                if (!StringUtils.isEmpty(sysUser.getUriPic())) {
                    //删除旧的图片
                    FastDFSKit.getInstance().deleteFromFastDfs(sysUser.getUriPic());
                }
                

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("添加头像失败了--", e);
            return ExceptionCodeEnum.FAILURE.getValue(); // 异常或操作失败
        }
        return ExceptionCodeEnum.SUCCESS.getValue();
    }

    /**
     * 上传图片，返回图片地址
     * @param img_portrait
     * @return
     * @throws IOException
     */
    private String uploadPic(String img_portrait) throws IOException {

        int len = 0;
        if (null != img_portrait) {
            // base64编码数据前几位数据为：data:image/jpeg;base64, 需要去掉这钱几位数据，否则转换成图片会损坏。
            if (img_portrait.startsWith("data:")) {
                int idx = img_portrait.indexOf(";base64,");
                if (idx > 0) {
                    len = idx + ";base64,".length();
                }
            }
            img_portrait = img_portrait.substring(len, img_portrait.length());
        } else {
            return null;
        }

        String new_pic = null;// 新的头像地址
        String filename = Base64ImgUtils.getFileNameFromBase64Img(img_portrait);
        byte[] imageByte = Base64ImgUtils.getFileByteFromBase64(img_portrait);
        if (imageByte.length == 0 || imageByte == null) {
            return null;
        }
        // 上传到FastDFS图片服务器
        new_pic = FastDFSKit.getInstance().uploadPicToFastDFS(imageByte, filename);
        logger.info("上传到FastDFS图片服务器>>" + new_pic);
        return new_pic;
    }


    /**
     * 修改密码发送手机验证码
     * @param username 用户名称
     * @return
     */
    @RequestMapping(value = "/varifyPhoneCode", method = RequestMethod.GET)
    @ResponseBody
    public String varifyPhoneCode(@CurrentUser User user,String username) {

        String sms_key = RedisKey.COMMON_CODE.getValue() + user.getPhone();
        final String code = RandomUtils.randomUtil();

        // 验证码存到redis缓存
        if (redisKit.push(sms_key, code, Long.parseLong(SMS_SECONDS))) {
            // 短信接口发送验证码
            smsService.verification_Code(user.getPhonecode(), user.getPhone(), String.valueOf(code));

            return ExceptionCodeEnum.SUCCESS.getValue();
        } else {
            return ExceptionCodeEnum.FAILURE.getValue();
        }
    }

    /**
     * 修改密码-手机号验证码判断
     * @return
     */
    @RequestMapping(value = "/userResetPwdSms", method = RequestMethod.POST)
    @ResponseBody
    public String userResetPwdSms(@CurrentUser User user, @Validated(value = CHANGE_PWD_SMS_GROUP.class) ValidWebBean bean, BindingResult br) {

//        if (br.hasErrors()) {
//            return ExceptionCodeEnum.NULL_PARAM.getValue();
//        }
//        //验证码是否正确
//        // 从redis缓存获取验证码
//        String smsKey = RedisKey.COMMON_CODE.getValue() + bean.getPhone();
//        String cacheCode = (String) redisKit.pull(smsKey);
//        if (StringUtils.isEmpty(cacheCode) || !cacheCode.equals(bean.getIcode())) {
//            return ExceptionCodeEnum.WRONG_CODE.getValue();
//        }
//
//        String new_password = EncryptionUtils.md5EnBit32(bean.getSetpassword());
//        if (!bean.getSetpassword().equals(bean.getPassword())) {
//            return ExceptionCodeEnum.WRONG_USERNAME_PWD.getValue(); //两次密码不一致
//        }
        
        return  null;
  
    }

    /**
     * 修改密码发送邮箱验证码
     * @return
     */
    @RequestMapping(value = "/varifyEmailCode", method = RequestMethod.GET)
    @ResponseBody
    public String varifyPhoneCode(@Validated(value = EMAIL_GROUP.class) ValidWebBean bean, BindingResult br) {

//        if (br.hasErrors()) {
//            return ExceptionCodeEnum.NULL_PARAM.getValue(); //参数为空或有误
//        }
//        UserAuths userAuths = userService.loadByUsername(bean.getEmail());
//        if (userAuths == null) {
//            return ExceptionCodeEnum.NOT_EXIST.getValue(); //邮箱不存在
//        }
//        String emailKey = RedisKey.COMMON_CODE.getValue() + bean.getEmail();
//        final String code = RandomUtils.randomUtil();
//
//        // 验证码存到redis缓存
//        if (redisKit.push(emailKey, code, Long.parseLong(EMAIL_SECONDS))) {
//            logger.debug(bean.getEmail() + "--varify code--" + code);
//            if (SendMailUtils.getInstance().sendCodeByMail(bean.getEmail(), code)) {
//                return ExceptionCodeEnum.SUCCESS.getValue();
//            }
//            return ExceptionCodeEnum.SUCCESS.getValue();
//        }
//        return ExceptionCodeEnum.FAILURE.getValue(); //操作失败
    	return null;
    }

    /**
     * 修改密码-邮箱验证码判断
     * @return
     */
    @RequestMapping(value = "/userResetPwdEmail", method = RequestMethod.POST)
    @ResponseBody
    public String userResetPwdEmail(@CurrentUser User user, @Validated(value = CHANGE_PWD_EMAIL_GROUP.class) ValidWebBean bean, BindingResult br) {

//        if (br.hasErrors()) {
//            return ExceptionCodeEnum.NULL_PARAM.getValue();
//        }
//        //验证码是否正确
//        String smsKey = RedisKey.COMMON_CODE.getValue() + bean.getEmail();
//        String cacheCode = (String) redisKit.pull(smsKey);
//        if (StringUtils.isEmpty(cacheCode) || !cacheCode.equals(bean.getIcode())) {
//            return ExceptionCodeEnum.WRONG_CODE.getValue();
//        }
//
//        String new_password = EncryptionUtils.md5EnBit32(bean.getSetpassword());
//        if (!bean.getSetpassword().equals(bean.getPassword())) {
//            return ExceptionCodeEnum.WRONG_USERNAME_PWD.getValue(); //两次密码不一致
//        }
//        UserAuths curUser = userService.loadByUsername(user.getUsername());
//        if (curUser.getPassword().equals(new_password)) {
//            return ExceptionCodeEnum.EXIST.getValue(); //和原密码一致
//        }
//        try {
//            //修改密码
//            userService.updateUserAuthsPwdByGuid(user.getUserGuid(), new_password);
//
//            return ExceptionCodeEnum.SUCCESS.getValue();
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.debug("邮箱方式，修改密码失败----", e);
//            return ExceptionCodeEnum.FAILURE.getValue();
//        }
    	return null;
    }

}
