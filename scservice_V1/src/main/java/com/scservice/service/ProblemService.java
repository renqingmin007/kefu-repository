package com.scservice.service;

import com.scservice.mapper.ProblemMapper;
import com.scservice.pojo.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/19
 * @Description: com.bjunicom.scservice.service
 * @version: 1.0
 */
public interface ProblemService {

    //添加问题
     void insertProblem(Problem problem);

    //修改问题
     void modifyProblem(Problem problem);

    //查询所有问题
     List<Problem> selectProblem();

    //根据问题id删除单个问题
     Integer deleteProblem(Integer problem_id);



}
