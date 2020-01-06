package com.scservice.mapper;

import com.scservice.pojo.Admin;
import com.scservice.pojo.AdminExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/2
 * @Description: com.bjunicom.scservice.dao
 * @version: 1.0
 */
@Mapper
public interface AdminMapper {

    //删除客服管理员
    void deleteAdmin(@Param("admin_id") Long admin_id);

    //添加客服管理员
    void insertAdmin(Admin a);

    //查询单个客服管理员
    Admin selectAdmin_name(@Param("admin_name") String admin_name);

    //根据id查询单个客服管理员
    Admin selectAdmin_id(@Param("admin_id") Long admin_id);

    //修改单个客服管理员
    Admin updateAdmin( Admin admin);

    //查询所有客服及管理员
    List<Admin> selectAdmin();

    //查询所有客服人员或者管理员
    List<Admin> selectAdmin_Server(Long role_id);

    //工单完成，业务量加1
    void addCount(@Param("admin_name") String admin_name);

    List<Admin> selectByExample(AdminExample example);

}
