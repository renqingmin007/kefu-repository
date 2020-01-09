package com.scservice.controller;

import com.scservice.pojo.Role;
import com.scservice.pojo.Admin;
import com.scservice.service.RoleService;
import com.scservice.service.AdminRoleService;
import com.scservice.service.AdminService;
import com.scservice.util.ResultModel;
import com.scservice.util.ResultTools;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("config")
public class AdminController {
	@Autowired
	AdminRoleService adminRoleService;
	@Autowired
	AdminService adminService;
	@Autowired
	RoleService roleService;
	//private Long role_id;
	@RequestMapping("listAdmin_agent")
	//列出所有客服人员
	public ResultModel list() {
		long role_id = 2;
		Map map = new HashMap();
		try {
			List<Admin> admin_server = adminService.selectAdmin_Server(role_id);
			map.put("admin_server",admin_server);
			return ResultTools.result(0, "查询成功", map);
		}
		catch(Exception e){
			return ResultTools.result(404, "查询失败", map);
		}
		}

	//列出所有管理员及客服信息
	@RequestMapping("listAdmin")
	public ResultModel list(Map map) {
		try{
		List<Admin> ad = adminService.list();
		map.put("admin", ad);
		Map<Admin, List<Role>> admin_roles = new HashMap<>();
		for (Admin admin : ad) {
			List<Role> roles = roleService.listRoles(admin);
			admin_roles.put(admin, roles);
		}
		map.put("admin_roles", admin_roles);
			return ResultTools.result(0, "查询成功",map);
		}catch (Exception e) {
			return ResultTools.result(404, "查询失败",null);
		}
	}

	//编辑管理员或客服
	@RequestMapping("editAdmin")
	public ResultModel edit(Map map, long id) {
		try{
		List<Role> rs = roleService.list();
		map.put("rs", rs);
		Admin admin = adminService.selectAdmin_id(id);
		map.put("admin", admin);

		List<Role> roles = roleService.listRoles(admin);
		map.put("currentRoles", roles);
			return ResultTools.result(0, "编辑成功",map);
		}catch (Exception e) {
			return ResultTools.result(404, "编辑失败", null);
		}
	}

	//根据id删除管理员或客服
	@RequestMapping("deleteAdmin")
	public ResultModel delete( Map map,long id) {
		try{
		adminService.deleteAdmin(id);
			return ResultTools.result(0, "删除成功",map);
		}catch (Exception e) {
			return ResultTools.result(404, "删除失败", null);
		}
	}

	//修改管理员或客服密码等
	@RequestMapping("updateAdmin")
	public ResultModel update(@RequestBody Admin admin) {
		Map map = new HashMap();
		try{
		//adminRoleService.setRoles(admin, roleIds);
		String password = admin.getPassword();
		// 如果在修改的时候没有设置密码，就表示不改动密码
		if (admin.getPassword().length() != 0) {
			String salt = new SecureRandomNumberGenerator().nextBytes().toString();
			int times = 2;
			String algorithmName = "md5";
			String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();
			admin.setSalt(salt);
			admin.setPassword(encodedPassword);
		} else
			admin.setPassword(null);

		adminService.updateAdmin(admin);
		map.put("admin",admin);
			return ResultTools.result(0, "更新成功",map);
		}catch (Exception e) {
			return ResultTools.result(500, "更新失败:"+e.getMessage(), null);
		}

	}

	//添加客服或管理员
	@RequestMapping("addAdmin")
	public ResultModel add(@RequestBody Admin admin) {
		Map map = new HashMap();
		try{
		String name = admin.getName();
		String password = admin.getPassword();
		String salt = new SecureRandomNumberGenerator().nextBytes().toString();
		int times = 2;
		String algorithmName = "md5";

		String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();

		admin.setName(name);
		admin.setPassword(encodedPassword);
		admin.setSalt(salt);
		adminService.insertAdmin(admin);
		map.put("ad",admin);
			return ResultTools.result(0, "添加成功",map);
		}catch (Exception e) {
			return ResultTools.result(404, "添加失败", null);
		}
	}



}