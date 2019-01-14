package com.bisa.health.shop.controller;

import com.bisa.health.app.enumerate.ActivateEnum;
import com.bisa.health.app.enumerate.ServiceType;
import com.bisa.health.app.model.ServiceCategory;
import com.bisa.health.app.model.ServiceDetail;
import com.bisa.health.client.entity.User;
import com.bisa.health.shiro.web.bind.CurrentUser;
import com.bisa.health.basic.entity.Pager;
import com.bisa.health.common.enumerate.ExceptionCodeEnum;
import com.bisa.health.common.utils.DateUtils;
import com.bisa.health.shop.model.ServiceCardRecord;
import com.bisa.health.shop.service.IServiceCardRecordService;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 服务激活卡
 * 
 * @author Administrator
 */

@Controller
@RequestMapping("/user")
public class ServiceActivateController {

//	@Autowired
//	private IServiceCardRecordService serviceCardRecordService;
//	@Resource
//	private IServiceDetailService serviceDetailService;
//	@Resource
//	private IServiceCategoryService serviceCategoryService;
//
//	private Logger logger = LogManager.getLogger(ServiceActivateController.class);
//
//	/**
//	 * 服务卡页面
//	 * 
//	 * @returnlistByUserguidAndCategory
//	 */
//	@RequestMapping(value = "/serviceCardPage", method = RequestMethod.GET)
//	public String serviceActivate(Model model, @CurrentUser User sysUser) {
//		model.addAttribute("currentAccount", sysUser.getUsername());
//		return "user-center/serviceCardPage";
//	}
//
//	/**
//	 * 获取该用户,所有的服务
//	 * 
//	 * @param sysUser
//	 * @return
//	 */
//	@RequestMapping(value = "/loadServiceDetails", method = RequestMethod.GET)
//	@ResponseBody
//	public List<ServiceDetail> serviceDetails(@CurrentUser User sysUser) {
//		// 获取该用户,所有的服务
//		List<ServiceDetail> serviceDetails = serviceDetailService.listByUserguidAndIsActive(sysUser.getUser_guid(),
//				ActivateEnum.ACTIVATE.getValue());
//		return serviceDetails;
//	}
//
//	/**
//	 * 加载服务分页数据 /user/loadUserServiceList?status=-1&page=0&limit=10
//	 * 
//	 * @param status
//	 *            服务的状态（-1 全部 0 未使用 1已经使用）
//	 * @param page
//	 * @param limit
//	 * @return
//	 */
//	@RequestMapping(value = "/loadUserServiceList", method = RequestMethod.GET)
//	@ResponseBody
//	public Pager<ServiceCardRecord> loadUserServiceList(@CurrentUser User sysUser, Integer page, Integer limit,Integer status) {
//
//		Pager<ServiceCardRecord> serviceCardRecord = serviceCardRecordService.getPagerServiceCardRecordByUserGuid(page,
//				limit, sysUser.getUser_guid(), status);
//		return serviceCardRecord;
//	}
//
//	/**
//	 * 激活服务卡到某个账号下
//	 * 
//	 * @param account
//	 *            数量
//	 * @param cardNumber
//	 *            服务卡号
//	 * @return
//	 */
//	@RequestMapping(value = "/activeServiceCard", method = RequestMethod.POST)
//	@ResponseBody
//	public String activeServiceCard( String account,String cardNumber,@CurrentUser User sysUser) {
//
//		// 激活服务到service_detail表
//		try {
//			ServiceCardRecord serviceCardRecord = serviceCardRecordService
//					.selectServiceCardRecordByCardNumber(cardNumber);
//			// 获取用户下面的激活明细集合，一种激活的方式就一个对象,相同的激活对象是不能重复的(xx报告，yy报告，zz报告 3种)
//			List<ServiceDetail> listServiceDetail = serviceDetailService.listByUserguid(sysUser.getUser_guid());
//			ServiceDetail serviceDetail = ServiceDetail.byCategory(listServiceDetail,
//					serviceCardRecord.getServiceToken());
//			List<ServiceCategory> listServiceCategory = serviceCategoryService.listServiceCategory();
//
//			boolean result = addUpdateServiceDetail(serviceDetail, sysUser.getUser_guid(), serviceCardRecord,
//					listServiceCategory);
//			if (result) {
//				// 修改激活卡使用记录表
//				serviceCardRecord.setAccount(account);
//				serviceCardRecord.setActiveTime(new Date());
//				serviceCardRecord.setCardStatus(1);
//				boolean updateResult = serviceCardRecordService.updateServiceCardRecord(serviceCardRecord);
//				if (updateResult) {
//					logger.debug("更新服务卡的记录---成功---" + serviceCardRecord.getCardNumber());
//					return ExceptionCodeEnum.SUCCESS.getValue();
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.debug("更新服务卡的记录---失败---", e);
//			return ExceptionCodeEnum.FAILURE.getValue();
//		}
//		return ExceptionCodeEnum.FAILURE.getValue();
//	}
//
//	/**
//	 * 激活码激活页面
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/serviceActiveCode", method = RequestMethod.GET)
//	public String activeCodeService(Model model, @CurrentUser User sysUser) {
//		model.addAttribute("currentAccount", sysUser.getUsername());
//		return "/user-center/serviceActiveCode";
//	}
//
//	/**
//	 * 使用卡号和激活码来激活服务
//	 * 
//	 * @param cardNumber
//	 *            卡号
//	 * @param cardCode
//	 *            激活码
//	 * @param account
//	 *            数量
//	 * @param setAccount
//	 *            设置数量
//	 * @return
//	 */
//	@RequestMapping(value = "/serviceActiveCodePost", method = RequestMethod.POST)
//	public String serviceActiveCodePost(Model model, @CurrentUser User sysUser,
//			String cardNumber,
//			String cardCode,
//			String account,
//			String setAccount) {
//
//		if (StringUtils.isEmpty(cardNumber) || StringUtils.isEmpty(cardCode) || StringUtils.isEmpty(account)
//				|| StringUtils.isEmpty(setAccount)) {
//			model.addAttribute("message", ExceptionCodeEnum.NULL_PARAM.getValue());
//			return "/user-center/serviceActiveCode";
//		}
//		if (!setAccount.equals(account)) {
//			model.addAttribute("message", ExceptionCodeEnum.WRONG_USERNAME_PWD.getValue());
//			return "/user-center/serviceActiveCode";
//		}
//
//		ServiceCardRecord serviceCardRecord = serviceCardRecordService.selectServiceCardRecordByCardNumber(cardNumber);
//		// 激活卡记录不存在/激活码不匹配/激活卡已激活
//		if (serviceCardRecord == null || !serviceCardRecord.getActiveCode().equals(cardCode)
//				|| serviceCardRecord.getCardStatus().equals(1)) {
//			model.addAttribute("message", ExceptionCodeEnum.WRONG_CODE.getValue());
//			return "/user-center/serviceActiveCode";
//		} else {
//
//			// 激活服务到service_detail表
//			List<ServiceDetail> listServiceDetail = serviceDetailService.listByUserguid(sysUser.getUser_guid());
//			ServiceDetail serviceDetail = ServiceDetail.byCategory(listServiceDetail,
//					serviceCardRecord.getServiceToken());
//			List<ServiceCategory> listServiceCategory = serviceCategoryService.listServiceCategory();
//
//			boolean result = addUpdateServiceDetail(serviceDetail,sysUser.getUser_guid(), serviceCardRecord,
//					listServiceCategory);
//			if (result) {
//				// 修改激活卡使用记录
//				serviceCardRecord.setAccount(setAccount);
//				serviceCardRecord.setActiveTime(new Date());
//				serviceCardRecord.setCardStatus(1);
//
//				result = serviceCardRecordService.updateServiceCardRecord(serviceCardRecord);
//				if (result) {
//					logger.debug("更新服务卡的记录---成功---" + serviceCardRecord.getCardNumber());
//					model.addAttribute("message", ExceptionCodeEnum.SUCCESS.getValue());
//					return "/user-center/serviceActiveCode";
//				}
//			}
//
//			model.addAttribute("mess更新服务卡的记录---失败---", ExceptionCodeEnum.FAILURE.getValue());
//			return "/user-center/serviceActiveCode";
//		}
//	}
//
//	/**
//	 * 添加或修改服务细节表
//	 * 
//	 * @param serviceDetail
//	 * @param userGuid
//	 * @param serviceCardRecord
//	 * @return
//	 */
//	public boolean addUpdateServiceDetail(ServiceDetail serviceDetail, int userGuid,
//			ServiceCardRecord serviceCardRecord, List<ServiceCategory> listServiceCategory) {
//		if (serviceDetail == null) {
//			// 新增服务对象
//			serviceDetail = new ServiceDetail();
//			serviceDetail.setUserGuid(userGuid);
//			serviceDetail.setServiceType(serviceCardRecord.getCardType());
//			serviceDetail.setIsActive(ActivateEnum.ACTIVATE);
//			serviceDetail.setVersion(1);
//			serviceDetail
//					.setCategory(ServiceCategory.byCategory(listServiceCategory, serviceCardRecord.getServiceToken()));
//
//			// 判断服务类型
//			if (serviceCardRecord.getCardType() == ServiceType.TIMING) {
//				// 时限
//				Date finishedTime = DateUtils.getCalendarFinishedTime(Calendar.MONTH, serviceCardRecord.getCount());
//				serviceDetail.setFinishedTime(finishedTime);
//			} else if (serviceCardRecord.getCardType() == ServiceType.COUNT) {
//				// 次数
//				serviceDetail.setCount(serviceCardRecord.getCount());
//			} else {
//				return false;
//			}
//			// 添加一个服务对象
//			serviceDetailService.saveAndUpServiceDetail(serviceDetail);
//			return true;
//
//		} else {
//			// 修改服务对象
//			if (serviceCardRecord.getCardType() == ServiceType.TIMING) { // 时限
//
//				// 这里要判断一下，服务的到期时间有没有过期
//				Date finishedTime = serviceDetail.getFinishedTime();
//				int compareDate = finishedTime.compareTo(new Date());// compareTo()方法的返回值，date1小于date2返回-1，date1大于date2返回1，相等返回0
//				if (compareDate >= 0) {
//					// 这里表示还没有过期服务
//					Calendar calendar = DateUtils.dataToCalendar(finishedTime); // 得到Calendar实例
//					calendar.add(Calendar.MONTH, serviceCardRecord.getCount()); // 月份累加
//					serviceDetail.setFinishedTime(calendar.getTime());
//				} else {
//					Calendar calendar = DateUtils.dataToCalendar(new Date()); // 得到Calendar实例
//					calendar.add(Calendar.MONTH, serviceCardRecord.getCount()); // 月份累加
//					serviceDetail.setFinishedTime(calendar.getTime());
//				}
//			} else if (serviceCardRecord.getCardType() == ServiceType.COUNT) { // 次数
//				serviceDetail.setCount(serviceDetail.getCount() + serviceCardRecord.getCount());
//			} else {
//				return false;
//			}
//
//			int version = serviceDetail.getVersion();
//			serviceDetail.setVersion(version + 1);
//			return serviceDetailService.updateServiceDetail(serviceDetail, version);
//		}
//	}

}
