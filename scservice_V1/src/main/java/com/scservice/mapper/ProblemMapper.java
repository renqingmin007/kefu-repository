package com.scservice.mapper;

import com.scservice.pojo.Problem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/30
 * @Description: com.scservice.mapper
 * @version: 1.0
 */
@Mapper
public interface ProblemMapper {

    //增加问题
    void insertProblem(Problem problem);

    //查询所有问题
    List<Problem> selectProblem();

    //根据问题id删除单个问题
    Integer deleteProblem(Integer problem_id);

    //根据问题id修改单个问题
    void modifyProblem(Problem problem);

}
