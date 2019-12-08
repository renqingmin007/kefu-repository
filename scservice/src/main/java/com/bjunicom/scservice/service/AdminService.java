package com.bjunicom.scservice.service;

import com.bjunicom.scservice.dao.AdminMapper;
import com.bjunicom.scservice.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/2
 * @Description: com.bjunicom.scservice.service
 * @version: 1.0
 */
@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;
    //添加管理员
    public void insertAdmin(Admin admin){
        adminMapper.insertAdmin(admin);
    }
    //删除管理员
    public void deleteAdmin(int admin_id){
        adminMapper.deleteAdmin(admin_id);
    }
    //查询所有管理员
    public List<Admin> selectAdmin(){
       return adminMapper.selectAdmin();
    }
    //查询单个管理员
    public List<Admin> selectAdmin_OA(String admin_oa){
        return adminMapper.selectAdmin_OA(admin_oa);
    }
}
