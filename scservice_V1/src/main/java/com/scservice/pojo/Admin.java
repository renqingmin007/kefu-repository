package com.scservice.pojo;

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
    private Long id;
    private String name;
    private String admin_oa;
    private String admin_phone;
    private String password;
    private String admin_status;
    private Long admin_count;
    private String salt;
    private Long role_id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getAdmin_count() { return admin_count; }

    public void setAdmin_count(Long admin_count) {
        this.admin_count = admin_count;
    }


    public String getAdmin_status() {
        return admin_status;
    }

    public void setAdmin_status(String admin_status) {
        this.admin_status = admin_status;
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

}
