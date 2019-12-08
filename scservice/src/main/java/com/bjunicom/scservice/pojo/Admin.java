package com.bjunicom.scservice.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/2
 * @Description: com.bjunicom.scservice.pojo
 * @version: 1.0
 */
public class Admin {
    //管理员信息
    private Integer admin_ID;
    private String admin_name;
    private String admin_oa;
    private String admin_phone;
    private String admin_passwd;
    private String admin_status;
    private String admin_count;
    private String role_id;

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getAdmin_count() {
        return admin_count;
    }

    public void setAdmin_count(String admin_count) {
        this.admin_count = admin_count;
    }


    public String getAdmin_status() {
        return admin_status;
    }

    public void setAdmin_status(String admin_status) {
        this.admin_status = admin_status;
    }



    public String getAdmin_passwd() {
        return admin_passwd;
    }

    public void setAdmin_passwd(String admin_passwd) {
        this.admin_passwd = admin_passwd;
    }


    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_oa() {
        return admin_oa;
    }

    public void setAdmin_oa(String admin_oa) {
        this.admin_oa = admin_oa;
    }

    public String getAdmin_phone() {
        return admin_phone;
    }

    public void setAdmin_phone(String admin_phone) {
        this.admin_phone = admin_phone;
    }

    public Integer getAdmin_ID() {
        return admin_ID;
    }

    public void setAdmin_ID(Integer admin_ID) {
        this.admin_ID = admin_ID;
    }
}
