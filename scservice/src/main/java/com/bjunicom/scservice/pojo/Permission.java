package com.bjunicom.scservice.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/8
 * @Description: com.bjunicom.scservice.pojo
 * @version: 1.0
 */
public class Permission {

    private Integer pid;
    private String permission_name;
    private String permission_desc;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }

    public String getPermission_desc() {
        return permission_desc;
    }

    public void setPermission_desc(String permission_desc) {
        this.permission_desc = permission_desc;
    }

}
