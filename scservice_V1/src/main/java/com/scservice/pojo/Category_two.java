package com.scservice.pojo;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/19
 * @Description: com.bjunicom.scservice.pojo
 * @version: 1.0
 */
public class Category_two {

    //二级分类
    private Integer category_two_ID;

    private String category_two_name;
    private Integer parent_one_ID;

    public Integer getCategory_two_ID() {
        return category_two_ID;
    }

    public void setCategory_two_ID(Integer category_two_ID) {
        this.category_two_ID = category_two_ID;
    }

    public String getCategory_two_name() {
        return category_two_name;
    }

    public void setCategory_two_name(String category_two_name) {
        this.category_two_name = category_two_name;
    }

    public Integer getParent_one_ID() {
        return parent_one_ID;
    }

    public void setParent_one_ID(Integer parent_one_ID) {
        this.parent_one_ID = parent_one_ID;
    }


}
