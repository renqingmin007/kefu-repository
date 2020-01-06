package com.scservice.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/8
 * @Description: com.bjunicom.scservice.pojo
 * @version: 1.0
 */
public class Role {


    private Long id;
    private String name;
    private String desc_;
    private Set<Permission> permission = new HashSet<>();

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

    public String getDesc_() {
        return desc_;
    }

    public void setDesc_(String desc_) {
        this.desc_ = desc_;
    }

    public Set<Permission> getPermission() {
        return permission;
    }

    public void setPermission(Set<Permission> permission) {
        this.permission = permission;
    }

}
