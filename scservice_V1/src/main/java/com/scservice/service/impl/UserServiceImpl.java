package com.scservice.service.impl;

import com.scservice.mapper.UserMapper;
import com.scservice.pojo.User;
import com.scservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/30
 * @Description: com.scservice.service.impl
 * @version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserMapper userMapper;

    //用户网页登录授权
    public void userLogin(User user){
        userMapper.userLogin(user);
    }

}
