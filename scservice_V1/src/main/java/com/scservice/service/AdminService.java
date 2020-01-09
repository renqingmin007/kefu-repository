package com.scservice.service;

import com.scservice.pojo.Admin;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/2
 * @Description: com.scservice.service
 * @version: 1.0
 */

public interface AdminService {
    //添加客服
     void insertAdmin(Admin admin);

    //删除客服
     void deleteAdmin(long admin_id);

    //查询所有客服及管理员
     List<Admin> selectAdmin();


    //客服或管理员修改密码等
     Admin updateAdmin(Admin admin);


    //根据id查询单个客服或管理员
     Admin selectAdmin_id(Long admin_id);

    //查询单个客服或管理员
     Admin selectAdmin_name(String admin_name);


    //查询所有客服
     List<Admin> selectAdmin_Server(Long role_id);

     List<Admin> list();
}
