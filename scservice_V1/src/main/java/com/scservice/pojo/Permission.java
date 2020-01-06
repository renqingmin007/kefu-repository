package com.scservice.pojo;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/8
 * @Description: com.scservice.pojo
 * @version: 1.0
 */
public class Permission {

    private Long id;
    private String name;
    private String desc_;
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
