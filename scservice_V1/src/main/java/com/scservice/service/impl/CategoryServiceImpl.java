package com.scservice.service.impl;

import com.scservice.mapper.CategoryMapper;
import com.scservice.pojo.Category_one;
import com.scservice.pojo.Category_three;
import com.scservice.pojo.Category_two;
import com.scservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/30
 * @Description: com.scservice.service.impl
 * @version: 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;


    @Override
    public void deleteCategory_one(Integer category_one_ID){
        categoryMapper.deleteCategory_one(category_one_ID);
    }
    @Override
    //添加一个一级分类
    public Category_one insertCategory_one(Category_one category_one)

    {
        return categoryMapper.insertCategory_one(category_one);
    }
    @Override
    //查询所有一级分类
    public List<Category_one> selectCategory_one(){
        return categoryMapper.selectCategory_one();
    }

    @Override
    //修改单个一级分类
    public Category_one modifyCategory_one(Integer category_one_ID){
        return categoryMapper.modifyCategory_one(category_one_ID);
    }
    @Override
    //删除某个二级分类
    public void deleteCategory_two(Integer category_two_ID){
        categoryMapper.deleteCategory_two(category_two_ID);
    }

    @Override
    //添加一个二级分类
    public Category_two insertCategory_two(Category_two category_two)

    {
        return categoryMapper.insertCategory_two(category_two);
    }
    @Override
    //查询所有二级分类
    public List<Category_two> selectCategory_two(){
        return categoryMapper.selectCategory_two();
    }
    @Override
    //修改单个二级分类
    public Category_two modifyCategory_two(Integer category_two_ID){
        return categoryMapper.modifyCategory_two(category_two_ID);
    }
    @Override
    //删除某个三级分类
    public void deleteCategory_three(Integer category_three_ID){
        categoryMapper.deleteCategory_three(category_three_ID);
    }
    @Override
    //添加一个三级分类
    public Category_three insertCategory_three(Category_three category_three)

    {
        return categoryMapper.insertCategory_three(category_three);
    }
    @Override
    //查询所有三级分类
    public List<Category_three> selectCategory_three(){
        return categoryMapper.selectCategory_three();
    }
    @Override
    //修改单个三级分类
    public Category_three modifyCategory_three(Integer category_three_ID){
        return categoryMapper.modifyCategory_three(category_three_ID);
    }
}
