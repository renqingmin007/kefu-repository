package com.scservice.pojo;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/19
 * @Description: com.bjunicom.scservice.pojo
 * @version: 1.0
 */
public class Problem {

    //问题描述
    private Integer problem_ID;
    private String problem_content;
    private String parent_three_ID;

    public Integer getProblem_ID() {
        return problem_ID;
    }

    public void setProblem_ID(Integer problem_ID) {
        this.problem_ID = problem_ID;
    }

    public String getProblem_content() {
        return problem_content;
    }

    public void setProblem_content(String problem_content) {
        this.problem_content = problem_content;
    }

    public String getParent_three_ID() {
        return parent_three_ID;
    }

    public void setParent_three_ID(String parent_three_ID) {
        this.parent_three_ID = parent_three_ID;
    }
}
