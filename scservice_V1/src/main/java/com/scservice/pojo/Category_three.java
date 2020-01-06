package com.scservice.pojo;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/19
 * @Description: com.bjunicom.scservice.pojo
 * @version: 1.0
 */
public class Category_three {

    //三级分类
    private Integer category_three_ID;
    private String category_three_name;
    private Integer parent_two_ID;


    public Integer getCategory_three_ID() {
        return category_three_ID;
    }

    public void setCategory_three_ID(Integer category_three_ID) {
        this.category_three_ID = category_three_ID;
    }

    public String getCategory_three_name() {
        return category_three_name;
    }

    public void setCategory_three_name(String category_three_name) {
        this.category_three_name = category_three_name;
    }

    public Integer getParent_two_ID() {
        return parent_two_ID;
    }

    public void setParent_two_ID(Integer parent_two_ID) {
        this.parent_two_ID = parent_two_ID;
    }
}
