package com.scservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.scservice.pojo.User;
import com.scservice.service.UserService;
import com.scservice.util.HttpRequest;
import com.scservice.util.ResultModel;
import com.scservice.util.ResultTools;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    //获取用户微信信息
    @RequestMapping(value = "userLogin", method = RequestMethod.POST, produces = "application/json")
    //1、用户登录授权接口
    public ResultModel userLogin(@RequestBody Map<String, Object> para_login) {
    //获取access_token
    //通过code获取openid
        //String account=para_login.get("account").toString();
        String code=para_login.get("code").toString();
        Map map = new HashMap();
    // 登录凭证不能为空
        if (code == null || code.length() == 0) {
            return ResultTools.result(101,"code不能为空",null);
        }
        String appid = "";
        String appsecret = "";
    // 发送请求获取accessToken
    // 授权（必填）
        String grant_type = "authorization_code";
    // 1、向微信服务器 使用登录凭证 code 获取 openid
        String params = "appid=" + appid + "&secret=" + appsecret + "&code=" + code + "&grant_type="
                + grant_type;
    // 发送请求
        String sr = HttpRequest.sendPost("https://api.weixin.qq.com/sns/oauth2/access_token",params);
    // 解析相应内容（转换成json对象）
        JSONObject json_id = JSONObject.parseObject(sr);
    // 用户的唯一标识（openid）
        String openId = (json_id.get("openid")).toString();
        String accessToken = (json_id.get("access_token")).toString();
    //构造获取用户基本信息api
        String lang = "zh_CN";
        String params_info = "access_token=" + accessToken + "&openid=" + openId +"&lang="+ lang;
        String content = "";
        ObjectMapper objectMapper = new ObjectMapper();
        /*
        List<User> userList =userService.FindUser(account);
        if(userList.size()==0) {
            return ResultTools.result(102,"没有此account用户",null);
        }else {
        */
            User user = null;
            try {
    //content就是json格式的用户信息
                content = HttpRequest.sendPost("https://api.weixin.qq.com/sns/userinfo",params_info);
                Map map_info = objectMapper.readValue(content, Map.class);
                Object open_Id = map_info.get("openid");
                Object nickName = map_info.get("nickname");
                if (openId.equals(open_Id) && nickName != null) {
    //获取微信用户基本信息成功
                    //user = userList.get(0);
                    if (map_info != null) {
                        user.setWx_country(String.valueOf(map_info.get("country")));
                        user.setWx_city(String.valueOf(map_info.get("city")));
                        user.setOpenid(String.valueOf(open_Id));
                        user.setWx_nickname(String.valueOf(map_info.get("nickname")));
                        userService.userLogin(user);
                        map.put("userinfo", user);
                    } else {
                        return ResultTools.result(102,"未获得用户信息",null);
                    }
                }
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return ResultTools.result(0,"获取成功",map);
    }


}
