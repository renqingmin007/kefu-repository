package com.scservice.service.impl;

import com.scservice.mapper.AdminRoleMapper;
import com.scservice.mapper.RoleMapper;
import com.scservice.pojo.*;
import com.scservice.service.RoleService;
import com.scservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleMapper roleMapper;
	@Autowired
    AdminRoleMapper adminRoleMapper;
	@Autowired
	AdminService adminService;

	@Override
	public Set<String> listRoleNames(String userName) {
		Set<String> result = new HashSet<>();
		List<Role> roles = listRoles(userName);
		for (Role role : roles) {
			result.add(role.getName());
		}
		return result;
	}

	@Override
	public List<Role> listRoles(String adminName) {
		List<Role> roles = new ArrayList<>();
		Admin admin = adminService.selectAdmin_name(adminName);
		if (null == admin)
			return roles;

		roles = listRoles(admin);
		return roles;
	}

	@Override
	public void add(Role u) {
		roleMapper.insert(u);
	}

	@Override
	public void delete(Long id) {
		roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(Role u) {
		roleMapper.updateByPrimaryKeySelective(u);
	}

	@Override
	public Role get(Long id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Role> list() {
		RoleExample example = new RoleExample();
		example.setOrderByClause("id desc");
		return roleMapper.selectByExample(example);
	}

	@Override
	public List<Role> listRoles(Admin admin) {
		List<Role> roles = new ArrayList<>();

		AdminRoleExample example = new AdminRoleExample();

		example.createCriteria().andUidEqualTo(admin.getId());
		List<AdminRole> adminRoles = adminRoleMapper.selectByExample(example);

		for (AdminRole adminRole : adminRoles) {
			Role role = roleMapper.selectByPrimaryKey(adminRole.getRid());
			roles.add(role);
		}
		return roles;
	}

}