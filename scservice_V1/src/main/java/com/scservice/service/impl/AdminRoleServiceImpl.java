package com.scservice.service.impl;

import com.scservice.mapper.AdminRoleMapper;
import com.scservice.pojo.Admin;
import com.scservice.pojo.AdminRole;
import com.scservice.pojo.AdminRoleExample;
import com.scservice.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {

	@Autowired
	AdminRoleMapper adminRoleMapper;

	@Override
	public void setRoles(Admin admin, long[] roleIds) {
		// 删除当前用户所有的角色
		AdminRoleExample example = new AdminRoleExample();
		example.createCriteria().andUidEqualTo(admin.getId());
		List<AdminRole> urs = adminRoleMapper.selectByExample(example);
		for (AdminRole adminRole : urs)
			adminRoleMapper.deleteByPrimaryKey(adminRole.getId());

		// 设置新的角色关系
		if (null != roleIds)
			for (long rid : roleIds) {
				AdminRole adminRole = new AdminRole();
				adminRole.setRid(rid);
				adminRole.setAid(admin.getId());
				adminRoleMapper.insert(adminRole);
			}
	}

	@Override
	public void deleteByAdmin(long adminId) {
		AdminRoleExample example = new AdminRoleExample();
		example.createCriteria().andUidEqualTo(adminId);
		List<AdminRole> ars = adminRoleMapper.selectByExample(example);
		for (AdminRole adminRole : ars) {
			adminRoleMapper.deleteByPrimaryKey(adminRole.getId());
		}
	}

	@Override
	public void deleteByRole(long roleId) {
		AdminRoleExample example = new AdminRoleExample();
		example.createCriteria().andRidEqualTo(roleId);
		List<AdminRole> ars = adminRoleMapper.selectByExample(example);
		for (AdminRole adminRole : ars) {
			adminRoleMapper.deleteByPrimaryKey(adminRole.getId());
		}

	}

}