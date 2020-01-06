package com.scservice.service;

import com.scservice.pojo.Role;
import com.scservice.pojo.Admin;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
public interface RoleService {
	public Set<String> listRoleNames(String adminName);

	public List<Role> listRoles(String adminName);

	public List<Role> listRoles(Admin admin);

	public List<Role> list();

	public void add(Role role);

	public void delete(Long id);

	public Role get(Long id);

	public void update(Role role);

}