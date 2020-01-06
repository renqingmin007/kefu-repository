package com.scservice.controller;

import com.scservice.pojo.Permission;
import com.scservice.pojo.Role;
import com.scservice.service.PermissionService;
import com.scservice.service.RolePermissionService;
import com.scservice.service.RoleService;
import com.scservice.util.ResultModel;
import com.scservice.util.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("config")
public class RoleController {
	@Autowired
	RoleService roleService;
	@Autowired
	RolePermissionService rolePermissionService;
	@Autowired
	PermissionService permissionService;

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

	@RequestMapping("updateRole")
	public ResultModel update(Map map,Role role, long[] permissionIds) {
        try {
		rolePermissionService.setPermissions(role, permissionIds);
		roleService.update(role);
		map.put("role",role);
        return ResultTools.result(0, "查询成功",map);
        }catch (Exception e) {
            return ResultTools.result(404, "查询失败",null);
        }
	}

	@RequestMapping("addRole")
	public ResultModel list(Map map, Role role) {
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

	@RequestMapping("deleteRole")
	public ResultModel delete(Map map, long id) {
        try{
		roleService.delete(id);
		map.put("id",id);
		return ResultTools.result(0, "删除成功",map);
        }catch (Exception e) {
            return ResultTools.result(404, "删除失败",null);
        }
	}
}