package com.bisa.health.shop.admin.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresRoles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.bisa.health.shop.admin.dto.AuthNode;
import com.bisa.health.shop.admin.dto.FormSelectsDto;
import com.bisa.health.shop.admin.util.AuthTreeUtils;


/**
 * 后台人员角色权限控制器
 * @author Administrator
 */

@RequiresRoles("ROLE_ADMIN")
@Controller
@RequestMapping(value = "/admin")
public class AdminUserController {

  /*  @Autowired
    private IUserService userService;*/

    /**
     * 人员列表页面
     * @return
     */
    @RequestMapping(value = "/adminUserList", method = RequestMethod.GET)
    public String listAdminUsers() {
        return "admin/admin_user/adminUserList";
    }

/*
    */
/**
     * 人员列表数据1 2
     * @return
     *//*

    @ApiOperation(value = "人员列表", notes = "加载人员列表数据")
    @RequestMapping(value = "/adminUserListJson", method = RequestMethod.GET)
    @ResponseBody
    public List<UserAuths> listAdminUsersJson() {
        List<UserAuths> adminUserList = new ArrayList<UserAuths>();
        //超级管理员
        List<UserAuths> adminList = userService.listRoleUsers(RoleTypeEnum.ROLE_ADMIN);
        adminUserList.addAll(adminList);
        //客服人员
        List<UserAuths> customerList = userService.listRoleUsers(RoleTypeEnum.ROLE_CUSTOMER);
        adminUserList.addAll(customerList);

        return adminUserList;
    }
*/

    /**
     * 添加人员 页面
     * @return
     */
    @RequestMapping(value = "/addAdminUser", method = RequestMethod.GET)
    public String addAdminUser() {
        return "admin/admin_user/addAdminUser";
    }

    /**
     * 角色权限 页面
     * @return
     */
    @RequestMapping(value = "/adminRoleManager", method = RequestMethod.GET)
    public String listAdminRoles() {
        return "admin/admin_user/adminRoleManager";
    }

    /**
     * 添加角色 页面
     * @return
     */
    @RequestMapping(value = "/addAdminRole", method = RequestMethod.GET)
    public String addAdminRole() {
        return "admin/admin_user/addAdminRole";
    }

    /**
     * 获得所有权限
     * @return
     */
    @RequestMapping(value = "/authListJson", method = RequestMethod.GET)
    @ResponseBody
    public FormSelectsDto listAuthJson() {
        FormSelectsDto formSelectsDto = new FormSelectsDto();

        List<AuthNode> nodeList = AuthTreeUtils.getInfiniteLevelTree();
        if (nodeList != null && nodeList.size() > 0) {
            formSelectsDto.setCode(200);
            formSelectsDto.setMsg("success");
            formSelectsDto.setData(nodeList);
        } else {
            formSelectsDto.setCode(400);
            formSelectsDto.setMsg("failure");
            formSelectsDto.setData(null);
        }
        return formSelectsDto;
    }

}
