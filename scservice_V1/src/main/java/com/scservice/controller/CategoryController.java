package com.scservice.controller;

import com.scservice.pojo.Category_one;
import com.scservice.pojo.Category_three;
import com.scservice.pojo.Category_two;
import com.scservice.service.CategoryService;
import com.scservice.util.ResultModel;
import com.scservice.util.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/20
 * @Description: com.bjunicom.scservice.controller
 * @version: 1.0
 */
@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //1、删除一级分类
    @RequestMapping(value = "deleteCategory_one", method = RequestMethod.POST, produces = "application/json")
    //@RequiresPermissions("admin:deleteCategory_one")//删除管理员权限注解
    public ResultModel deleteCategory_one(Integer category_one_ID) {
        try {
            categoryService.deleteCategory_one(category_one_ID);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data",category_one_ID);
            return ResultTools.result(0, "",map);
            //删除成功
        }catch (Exception e) {
            return ResultTools.result(404,e.getMessage(),null);
            //删除失败
        }
    }
    //2、添加一级分类
    @RequestMapping(value = "addCategory_one", method = RequestMethod.POST, produces = "application/json")
    //@RequiresPermissions("admin:addCategory_one")//添加管理员权限注解
    public ResultModel addCategory_one(@RequestBody Category_one category_one)
    {
        categoryService.insertCategory_one(category_one);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data",category_one);
        return ResultTools.result(0, "",map);
    }

    //3、查看一级分类
    @RequestMapping(value = "selectCategory_one", method = RequestMethod.GET, produces = "application/json")
    public ResultModel  selectCategory_one(){
        try {
            List<Category_one> category_one_all = categoryService.selectCategory_one();
            Map<String,Object> map = new HashMap<>();
            map.put("data",category_one_all);
            return ResultTools.result(0, "成功",map);
            //返回所有问题列表
        }catch (Exception e) {
            return ResultTools.result(404, "失败",null);
            //查看失败
        }
    }

    //4、修改一级分类
    @RequestMapping(value = "modifyCategory_one", method = RequestMethod.POST, produces = "application/json")
    public ResultModel ModifyCategory_one(@RequestBody Integer category_one_ID)
    {
        try {
            Category_one category_one = categoryService.modifyCategory_one(category_one_ID);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data", category_one);
            return ResultTools.result(0, "",map);

        }catch (Exception e) {
            return ResultTools.result(404,e.getMessage(),null);
        }
    }



    //1、删除二级分类
    @RequestMapping(value = "deleteCategory_two", method = RequestMethod.POST, produces = "application/json")
    //@RequiresPermissions("admin:deleteCategory_two")//删除管理员权限注解
    public ResultModel deleteCategory_two(Integer category_two_ID) {
        try {
            categoryService.deleteCategory_two(category_two_ID);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data",category_two_ID);
            return ResultTools.result(0, "",map);
            //删除成功
        }catch (Exception e) {
            return ResultTools.result(404,e.getMessage(),null);
            //删除失败
        }
    }
    //2、添加二级分类
    @RequestMapping(value = "addCategory_two", method = RequestMethod.POST, produces = "application/json")
    //@RequiresPermissions("admin:addCategory_two")//添加管理员权限注解
    public ResultModel addCategory_three(@RequestBody Category_two category_two)
    {
        categoryService.insertCategory_two(category_two);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data",category_two);
        return ResultTools.result(0, "",map);
    }

    //3、查看二级分类
    @RequestMapping(value = "selectCategory_two", method = RequestMethod.GET, produces = "application/json")
    public ResultModel  selectCategory_two(){
        try {
            List<Category_two> category_two_all = categoryService.selectCategory_two();
            Map<String,Object> map = new HashMap<>();
            map.put("data",category_two_all);
            return ResultTools.result(0, "成功",map);
            //返回所有问题列表
        }catch (Exception e) {
            return ResultTools.result(404, "失败",null);
            //查看失败
        }
    }

    //4、修改二级分类
    @RequestMapping(value = "modifyCategory_two", method = RequestMethod.POST, produces = "application/json")
    public ResultModel ModifyCategory_two(@RequestBody Integer category_two_ID)
    {
        try {
            Category_two category_two = categoryService.modifyCategory_two(category_two_ID);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data", category_two);
            return ResultTools.result(0, "",map);

        }catch (Exception e) {
            return ResultTools.result(404,e.getMessage(),null);
        }
    }


    //1、删除三级分类
    @RequestMapping(value = "deleteCategory_three", method = RequestMethod.POST, produces = "application/json")
    //@RequiresPermissions("admin:deleteCategory_two")//删除管理员权限注解
    public ResultModel deleteCategory_three(Integer category_three_ID) {
        try {
            categoryService.deleteCategory_three(category_three_ID);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data",category_three_ID);
            return ResultTools.result(0, "",map);
            //删除成功
        }catch (Exception e) {
            return ResultTools.result(404,e.getMessage(),null);
            //删除失败
        }
    }
    //2、添加三级分类
    @RequestMapping(value = "addCategory_three", method = RequestMethod.POST, produces = "application/json")
    //@RequiresPermissions("admin:addCategory_one")//添加管理员权限注解
    public ResultModel addCategory_three(@RequestBody Category_three category_three)
    {
        categoryService.insertCategory_three(category_three);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data",category_three);
        return ResultTools.result(0, "",map);
    }

    //3、查看三级分类
    @RequestMapping(value = "selectCategory_three", method = RequestMethod.GET, produces = "application/json")
    public ResultModel  findCategory_two(){
        try {
            List<Category_three> category_three_all = categoryService.selectCategory_three();
            Map<String,Object> map = new HashMap<>();
            map.put("data",category_three_all);
            return ResultTools.result(0, "查看成功",map);
            //返回所有问题列表
        }catch (Exception e) {
            return ResultTools.result(404, "查看失败",null);
            //查看失败
        }
    }

    //4、修改三级分类
    @RequestMapping(value = "modifyCategory_three", method = RequestMethod.POST, produces = "application/json")
    public ResultModel ModifyCategory_three(@RequestBody Integer category_three_ID)
    {
        try {
            Category_three category_three = categoryService.modifyCategory_three(category_three_ID);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data", category_three);
            return ResultTools.result(0, "",map);

        }catch (Exception e) {
            return ResultTools.result(404,e.getMessage(),null);
        }
    }

    }
