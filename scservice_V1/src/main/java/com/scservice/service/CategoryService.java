package com.scservice.service;

import com.scservice.pojo.Category_one;
import com.scservice.pojo.Category_three;
import com.scservice.pojo.Category_two;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/20
 * @Description: com.scservice.service
 * @version: 1.0
 */
public interface CategoryService {

    //删除某个一级分类
     void deleteCategory_one(Integer category_one_ID);

    //添加一个一级分类
     Category_one insertCategory_one(Category_one category_one);

    //查询所有一级分类
     List<Category_one> selectCategory_one();


    //修改单个一级分类
     Category_one modifyCategory_one(Integer category_one_ID);


    //删除某个二级分类
     void deleteCategory_two(Integer category_two_ID);


    //添加一个二级分类
     Category_two insertCategory_two(Category_two category_two);

    //查询所有二级分类
     List<Category_two> selectCategory_two();

    //修改单个二级分类
     Category_two modifyCategory_two(Integer category_two_ID);

    //删除某个三级分类
     void deleteCategory_three(Integer category_three_ID);

    //添加一个三级分类
     Category_three insertCategory_three(Category_three category_three);

    //查询所有三级分类
     List<Category_three> selectCategory_three();

    //修改单个三级分类
     Category_three modifyCategory_three(Integer category_three_ID);
}
