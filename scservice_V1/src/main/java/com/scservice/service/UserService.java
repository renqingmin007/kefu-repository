package com.scservice.service;

import com.scservice.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @Auther: 任庆民
 * @Date: 2019/12/19
 * @Description: com.bjunicom.scservice.service
 * @version: 1.0
 */
public interface UserService {

    //用户网页登录授权
    void userLogin(User user);
}
