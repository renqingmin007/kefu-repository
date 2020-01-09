package com.scservice.service.impl;

import com.scservice.mapper.AdminMapper;
import com.scservice.pojo.Admin;
import com.scservice.pojo.AdminExample;
import com.scservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/26
 * @Description: com.scservice.service.impl
 * @version: 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    //添加客服
    @Override
    public void insertAdmin(Admin admin){
        adminMapper.insertAdmin(admin);
    }

    //删除客服
    @Override
    public void deleteAdmin(long admin_id){
        adminMapper.deleteAdmin(admin_id);
    }

    //查询所有客服及管理员
    @Override
    public List<Admin> selectAdmin(){
        return adminMapper.selectAdmin();
    }


    //客服或管理员修改密码
    @Override
    public Admin updateAdmin(Admin admin){
        adminMapper.updateByPrimaryKeySelective(admin);
        return null;
    }


    //根据id查询单个客服或管理员
    @Override
    public Admin selectAdmin_id(Long admin_id){
        return adminMapper.selectAdmin_id(admin_id);
    }


    //查询单个客服或管理员
    @Override
    public Admin selectAdmin_name(String admin_name){
        AdminExample example = new AdminExample();
        example.createCriteria().andNameEqualTo(admin_name);
        Admin admin = adminMapper.selectAdmin_name(admin_name);
        if (admin == null)
            return null;
        return admin;
    }


    //查询所有客服
    @Override
    public List<Admin> selectAdmin_Server(Long role_id){return  adminMapper.selectAdmin_Server(role_id);}

    @Override
    public List<Admin> list(){
        AdminExample example = new AdminExample();
        example.setOrderByClause("id desc");
        return adminMapper.selectByExample(example);
    }
}
