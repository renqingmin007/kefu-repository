package com.scservice.controller;

import com.scservice.pojo.Permission;
import com.scservice.service.PermissionService;
import com.scservice.util.ResultModel;
import com.scservice.util.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("config")
public class PermissionController {
	@Autowired
	PermissionService permissionService;

	@RequestMapping("listPermission")
	public ResultModel list() {
		try {
		List<Permission> ps = permissionService.list();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", ps);
			return ResultTools.result(0, "查看成功",map);
		}catch (Exception e) {
			return ResultTools.result(404, "查看失败",null);

		}
	}

	@RequestMapping("editPermission")
	public ResultModel list(Map map,long id) {
		try {
		Permission permission = permissionService.get(id);
		map.put("data", permission);
		    return ResultTools.result(0, "编辑成功",map);
		}catch (Exception e) {
			return ResultTools.result(404, "编辑失败",null);
		}
	}

	@RequestMapping("updatePermission")
	public ResultModel update(@RequestBody Permission permission) {
		Map map =  new HashMap();
		try {
		permissionService.update(permission);
		map.put("data", permission);
		return ResultTools.result(0, "更新成功",map);
		}catch (Exception e) {
			return ResultTools.result(404, "更新失败",null);
		}
	}

	//增加一个权限
	@RequestMapping("addPermission")
	public ResultModel add(@RequestBody Permission permission) {
		Map map = new HashMap();
		try {
		System.out.println(permission.getName());
		System.out.println(permission.getDesc_());
		permissionService.add(permission);
		map.put("data",permission);
		return ResultTools.result(0, "添加成功",map);
		}catch (Exception e) {
			return ResultTools.result(404, "添加失败",null);
		}
	}

	@RequestMapping("deletePermission")
	public ResultModel delete(Map map, long id) {
		try {
		permissionService.delete(id);
		return ResultTools.result(0, "删除成功",map);
		}catch (Exception e) {
			return ResultTools.result(404, "删除失败",null);
		}
	}

}