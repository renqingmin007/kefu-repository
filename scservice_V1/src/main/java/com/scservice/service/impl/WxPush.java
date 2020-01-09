package com.scservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface WxpushServiceImpl implements WxpushService{
    //获取accesstoken
    void getaccesstoken(String appid,String appscreat);
    //删除工单
    void sendmessage(Long workOrderId);
}
