package com.bjunicom.scservice.shiro;

import com.bjunicom.scservice.pojo.Admin;
import com.bjunicom.scservice.pojo.Permission;
import com.bjunicom.scservice.pojo.Role;
import com.bjunicom.scservice.service.AdminService;
import com.bjunicom.scservice.utils.JWTUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MyRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LogManager.getLogger(MyRealm.class);

    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {

        this.adminService = adminService;
    }

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {

        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JWTUtil.getAdmin_oa(principals.toString());
        Admin admin = adminService.selectAdmin_OA(username).get(0);

        if (admin != null){
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Collection<String> rolesCollection = new HashSet<String>();

        Collection<String> perStringCollection = new HashSet<String>();
        Set<Role> roles = admin.getRoles();

        //遍历集合

    for (Role role : roles) {

        //将每一个role的name装进collection集合

        rolesCollection.add(role.getRole_name());

        //获取每一个Role的permission的set集合

        Set<Permission> permissionSet = role.getPermission();

        //遍历集合

        for (Permission permission : permissionSet) {

            //将每一个permission的name装进collection集合

            perStringCollection.add(permission.getPermission_name());

        }

        //为用户授权

        info.addStringPermissions(perStringCollection);

    }

    //为用户授予角色

        info.addRoles(rolesCollection);
        return info;
        }else{
        return null;}
    }


    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得admin_oa，用于和数据库进行对比
        String admin_oa = JWTUtil.getAdmin_oa(token);
        if ( admin_oa== null) {
            throw new AuthenticationException("token异常");
        }

        List<Admin> adminList = adminService.selectAdmin_OA(admin_oa);
        if (adminList == null) {
            throw new AuthenticationException("token异常");
        }

        if (! JWTUtil.verify(token, admin_oa, adminList.get(0).getAdmin_passwd())) {
            throw new AuthenticationException("token异常");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
