package com.scservice.controller;

import com.scservice.pojo.Permission;
import com.scservice.pojo.Role;
import com.scservice.service.PermissionService;
import com.scservice.service.RolePermissionService;
import com.scservice.service.RoleService;
import com.scservice.util.ResultModel;
import com.scservice.util.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("config")
public class RoleController {
	@Autowired
	RoleService roleService;
	@Autowired
	RolePermissionService rolePermissionService;
	@Autowired
	PermissionService permissionService;

	//角色列表及其权限
	@RequestMapping("listRole")
	public ResultModel list(Map map) {
        try {
		List<Role> rs = roleService.list();
		map.put("rs", rs);
		Map<Role, List<Permission>> role_permissions = new HashMap<>();

		for (Role role : rs) {
			List<Permission> ps = permissionService.list(role);
			role_permissions.put(role, ps);
		}
		map.put("role_permissions", role_permissions);
        return ResultTools.result(0, "查询成功",map);
        }catch (Exception e) {
            return ResultTools.result(404, "查询失败",null);
        }

	}

	@RequestMapping("editRole")
	public ResultModel list(Map map, long id) {

        try {
		Role role = roleService.get(id);
		map.put("role", role);

		List<Permission> ps = permissionService.list();
		map.put("ps", ps);

		List<Permission> currentPermissions = permissionService.list(role);
		map.put("currentPermissions", currentPermissions);
        return ResultTools.result(0, "查询成功",map);
        }catch (Exception e) {
            return ResultTools.result(404, "查询失败",null);
        }
	}

	//更改角色信息及其所有的相关权限
	@RequestMapping("updateRole")
	public ResultModel update(@RequestBody  Role role, @RequestParam long[] permissionIds) {
        try {
        Map map = new HashMap();
		rolePermissionService.setPermissions(role, permissionIds);
		roleService.update(role);
		map.put("role",role);
        return ResultTools.result(0, "更新成功",map);
        }catch (Exception e) {
            return ResultTools.result(404, "更新失败",null);
        }
	}

	//角色添加接口（增加一个角色）
	@RequestMapping("addRole")
	public ResultModel list( @RequestBody Role role) {
		Map map = new HashMap();
	    try{
		System.out.println(role.getName());
		System.out.println(role.getDesc_());
		roleService.add(role);
		map.put("role",role);
        return ResultTools.result(0, "添加成功",map);
        }catch (Exception e) {
            return ResultTools.result(404, "添加失败",null);
        }
	}

	//删除某个角色
	@RequestMapping("deleteRole")
	public ResultModel delete( @RequestBody long id) {

		Map map = new HashMap();
        try{
		roleService.delete(id);
		map.put("id",id);
		return ResultTools.result(0, "删除成功",map);
        }catch (Exception e) {
            return ResultTools.result(404, "删除失败",null);
        }
	}
}