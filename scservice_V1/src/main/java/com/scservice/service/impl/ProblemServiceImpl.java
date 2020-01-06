package com.scservice.service.impl;

import com.scservice.mapper.ProblemMapper;
import com.scservice.pojo.Problem;
import com.scservice.service.ProblemService;
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
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    ProblemMapper problemMapper;

    //添加问题
    public Problem insertProblem(Problem problem){

        return problemMapper.insertProblem(problem);
    }

    //修改问题
    public Problem modifyProblem(Integer problem_id){

        return problemMapper.modifyProblem(problem_id);
    }

    //查询所有问题
    public List<Problem> selectProblem(){
        return problemMapper.selectProblem();
    }

    //根据问题id删除单个问题
    public Integer deleteProblem(Integer problem_id){
        return problemMapper.deleteProblem(problem_id);
    }


}
