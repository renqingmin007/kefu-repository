package com.bjunicom.scservice.dao;

import com.bjunicom.scservice.pojo.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/2
 * @Description: com.bjunicom.scservice.dao
 * @version: 1.0
 */
@Component("AdminMapper")
public interface AdminMapper {

    //删除管理员
    void deleteAdmin(@Param( "admin_id" )Integer admin_id);
    //添加管理员
    void insertAdmin(Admin a);
    //查询单个管理员
    List<Admin> selectAdmin_OA(@Param( "admin_oa" )String admin_oa);
    //查询所有管理员
    List<Admin> selectAdmin();
}
