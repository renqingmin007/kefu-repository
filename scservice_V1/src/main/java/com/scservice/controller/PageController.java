package com.scservice.controller;

import com.scservice.pojo.Admin;
import com.scservice.pojo.Permission;
import com.scservice.pojo.Role;
import com.scservice.service.AdminRoleService;
import com.scservice.service.AdminService;
import com.scservice.service.PermissionService;
import com.scservice.service.RoleService;
import com.scservice.util.ResultModel;
import com.scservice.util.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//专门用于显示页面的控制器
@RestController
@RequestMapping("config")
public class PageController {

	@Autowired
	AdminRoleService adminRoleService;
	@Autowired
	AdminService adminService;
	@Autowired
	RoleService roleService;
	@Autowired
	PermissionService permissionService;

	//后台用户管理总接口
	@RequestMapping("adminConfig")
	public ResultModel adminConf (Map map){
		try {
			List<Admin> ad = adminService.list();
			map.put("admin", ad);
			Map<Admin, List<Role>> admin_roles = new HashMap<>();
			for (Admin admin : ad) {
				List<Role> roles = roleService.listRoles(admin);
				admin_roles.put(admin, roles);
			}
			map.put("admin_roles", admin_roles);
			return ResultTools.result(0, "用户管理成功",map);
		} catch (Exception e) {
			return ResultTools.result(404, "用户管理失败", null);
		}
	}

	//角色管理总接口
	@RequestMapping("roleConfig")
	public ResultModel roleConf (Map map){
		try {
			List<Role> rs = roleService.list();
			map.put("rs", rs);
			Map<Role, List<Permission>> role_permissions = new HashMap<>();

			for (Role role : rs) {
				List<Permission> ps = permissionService.list(role);
				role_permissions.put(role, ps);
			}
			map.put("role_permissions", role_permissions);
			return ResultTools.result(0, "角色管理成功", map);
		} catch (Exception e) {
			return ResultTools.result(404, "角色管理失败", null);
		}
	}

	//权限管理总接口
	@RequestMapping("permsConfig")
	public ResultModel permsConf (Map map){
		try {
			List<Permission> ps = permissionService.list();
			map.put("data", ps);
			return ResultTools.result(0, "权限管理成功", map);
		} catch (Exception e) {
			return ResultTools.result(404, "权限管理失败", null);
		}
	}
	//未授权
	@RequestMapping("unauthorized")
	public String noPerms() {
		return "unauthorized";
	}

}
