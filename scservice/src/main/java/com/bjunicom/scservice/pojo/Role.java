package com.bjunicom.scservice.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/8
 * @Description: com.bjunicom.scservice.pojo
 * @version: 1.0
 */
public class Role {

    private Integer rid;
    private String role_name;
    private String role_desc;
    private Set<Permission> permission = new HashSet<>();

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_desc() {
        return role_desc;
    }

    public void setRole_desc(String role_desc) {
        this.role_desc = role_desc;
    }

    public Set<Permission> getPermission() {
        return permission;
    }

    public void setPermission(Set<Permission> permission) {
        this.permission = permission;
    }


}
