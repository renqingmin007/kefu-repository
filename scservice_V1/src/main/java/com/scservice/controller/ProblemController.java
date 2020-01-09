package com.scservice.controller;

import com.scservice.pojo.Problem;
import com.scservice.service.ProblemService;
import com.scservice.util.ResultModel;
import com.scservice.util.ResultTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/19
 * @Description: com.bjunicom.scservice.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("config")
public class ProblemController {

    @Autowired
    ProblemService problemService;

    //1、删除单个问题接口
    @RequestMapping(value = "deleteProblem", method = RequestMethod.POST, produces = "application/json")
    //@RequiresPermissions("admin:deleteAdmin")//删除管理员权限注解
    public ResultModel deleteProblem(@RequestParam Integer problem_id) {
        //认证逻辑
        try {
            problemService.deleteProblem(problem_id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data",problem_id);
            return ResultTools.result(0, "删除成功",map);
            //删除成功
        }catch (Exception e) {
            return ResultTools.result(404,"删除失败",null);
            //删除失败
        }
    }
    //2、增加问题接口
    @RequestMapping(value = "addProblem", method = RequestMethod.POST, produces = "application/json")
    //@RequiresPermissions("admin:addProblem")//添加管理员权限注解
    public ResultModel add(@RequestBody Problem problem)
    {
        problemService.insertProblem(problem);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data",problem);
        return ResultTools.result(0, "",map);
    }

    //3、查看所有问题接口

    @RequestMapping(value = "selectProblem", method = RequestMethod.GET, produces = "application/json")
    public ResultModel  findProblem(){
        try {
            List<Problem> problem_all = problemService.selectProblem();
            Map<String,Object> map = new HashMap<>();
            map.put("data",problem_all);
            return ResultTools.result(0, "成功",map);
            //返回所有问题列表
        }catch (Exception e) {
            return ResultTools.result(404, "失败",null);
            //查看失败
        }
    }

    //4、修改单个问题
    @RequestMapping(value = "modifyProblem", method = RequestMethod.POST, produces = "application/json")
    public ResultModel ModifyOrder(@RequestBody Problem problem)
    {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            problemService.modifyProblem(problem);
            map.put("data", problem);
            return ResultTools.result(0, "",map);
        }catch (Exception e) {
            return ResultTools.result(404,e.getMessage(),null);
        }
    }
}
