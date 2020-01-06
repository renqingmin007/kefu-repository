package com.scservice.mapper;

import com.scservice.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/19
 * @Description: com.bjunicom.scservice.dao
 * @version: 1.0
 */
@Mapper
public interface UserMapper {

    //用户网页登录授权
    void userLogin(User user);

}
