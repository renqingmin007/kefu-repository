package com.bjunicom.scservice.controller;

import com.bjunicom.scservice.pojo.Admin;
import com.bjunicom.scservice.service.AdminService;
import com.bjunicom.scservice.utils.JWTUtil;
import com.bjunicom.scservice.utils.ResultModel;
import com.bjunicom.scservice.utils.ResultTools;
import com.bjunicom.scservice.utils.UnauthorizedException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @Auther: 任庆民
 * @Date: 2019/12/2
 * @Description: com.bjunicom.scservice.controller
 * @version: 1.0
 */
@RestController
public class AdminController {
    //@Resource
    //private AdminService adminService;
    @Autowired
    private AdminService adminService;
    //1、管理员登录认证接口
    @RequestMapping(value = "loginAdmin", method = RequestMethod.GET, produces = "application/json")
    public ResultModel loginAdmin(@RequestBody Admin admin) {
        String account=admin.getAdmin_oa();
        String password=admin.getAdmin_passwd();
        if(account==null||account==""||password==null||password==""){
            return ResultTools.result(404,"账号和密码不能为空！",null);
        }
        List<Admin> userList = adminService.selectAdmin_OA(account);
        if(userList.size()==0) {
            return ResultTools.result(404, "用户名不存在", null);
        }else if(!userList.get(0).getAdmin_passwd().equals(password)){
            return ResultTools.result(404,"密码错误",null);
        }else if (userList.get(0).getAdmin_passwd().equals(password)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("token", JWTUtil.sign(account, password));
            return ResultTools.result(0, "登陆成功",map);
        } else {
            throw new UnauthorizedException();
        }

    }

    //退出登录

    @RequestMapping(value = "logoutAdmin", method = RequestMethod.POST, produces = "application/json")

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
        subject.logout();
        }
        response.sendRedirect("待定");
    }
    //访问login时跳到login

    @RequestMapping({"/login","/"})
    public String login() {

        //userService.getPositionRole();//获得岗位名称
        String role = "admin";//临时都给admin角色，后期改为根据岗位算出角色
        UsernamePasswordToken token = new UsernamePasswordToken(role, "123456");
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return "index";

    }

    //2、删除管理员接口
    @RequestMapping(value = "deleteAdmin", method = RequestMethod.POST, produces = "application/json")
    //@RequiresPermissions("admin:deleteAdmin")//删除管理员权限注解
    public ResultModel delete(Integer admin_id) {
        //认证逻辑
        try {
            adminService.deleteAdmin(admin_id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("deletemsg",admin_id);
            return ResultTools.result(0, "",map);
            //删除成功
        }catch (Exception e) {
            return ResultTools.result(404,e.getMessage(),null);
            //删除失败
        }
    }
    //3、添加管理员接口
    @RequestMapping(value = "addAdmin", method = RequestMethod.POST, produces = "application/json")
    //@RequiresPermissions("admin:addAdmin")//添加管理员权限注解
    public ResultModel add(String admin_oa,String admin_name,String admin_phone,
                           String admin_passwd,String admin_status,String admin_count,String admin_role)
    {
        //认证逻辑
//        try {
        Admin admin = new Admin();
        admin.setAdmin_oa(admin_oa);
        admin.setAdmin_name(admin_name);
        admin.setAdmin_phone(admin_phone);
        admin.setAdmin_passwd(admin_passwd);
        admin.setAdmin_status(admin_status);
        admin.setAdmin_count(admin_count);
        //System.out.println("密码：" + password);
        adminService.insertAdmin(admin);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("insertmsg",admin);
        return ResultTools.result(0, "",map);
    }
    //4、查看所有管理员接口

    @RequestMapping(value = "selectAdmin", method = RequestMethod.GET, produces = "application/json")
    public List<Admin> findAdmin(){
        try {
            List<Admin> admin_all = adminService.selectAdmin();
            return admin_all;
            //返回所有管理员列表
        }catch (Exception e) {
            return null;
            //查看失败
        }
    }

    //5、查询某个管理员接口
    @RequestMapping(value = "selectAdmin_OA", method = RequestMethod.GET, produces = "application/json")
    public List<Admin> findAdmin_OA(String admin_oa){
        try {
            List<Admin> admin = adminService.selectAdmin_OA(admin_oa);
            return admin;
            //返回所有管理员列表
        }catch (Exception e) {
            return null;
            //查看失败
        }
    }




    //有admin_super角色才能访问

    @RequestMapping("/admin_super")
    @ResponseBody
    @RequiresRoles("admin_super")
    public String admin() {
        return "admin_super success";

    }

    //有admin_agent角色才能访问

    @RequestMapping("/admin_agent")
    @ResponseBody
    @RequiresRoles("admin_agent")
    public String develop() {
        return "admin_agent success";
    }


}
