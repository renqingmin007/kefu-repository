package com.scservice.mapper;

import com.scservice.pojo.AdminRole;
import com.scservice.pojo.AdminRoleExample;
import com.scservice.pojo.RoleExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AdminRoleMapper {
	int deleteByPrimaryKey(Long id);

	int insert(AdminRole record);

	int insertSelective(AdminRole record);

	List<AdminRole> selectByExample(AdminRoleExample example);

	AdminRole selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(AdminRole record);

	int updateByPrimaryKey(AdminRole record);
}