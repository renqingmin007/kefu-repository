package com.scservice.service;

import com.scservice.pojo.Admin;


public interface AdminRoleService {

	public void setRoles(Admin admin, long[] roleIds);

	public void deleteByAdmin(long adminId);

	public void deleteByRole(long roleId);

}