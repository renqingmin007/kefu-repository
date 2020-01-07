package com.scservice.controller;

import com.scservice.util.ResultModel;
import com.scservice.util.ResultTools;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
	@RequestMapping(value = "/login")
	public ResultModel login(Map map, String name, String password) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(name, password);
		try {
			subject.login(token);
			Session session = subject.getSession();
			session.setAttribute("subject", subject);
			return ResultTools.result(0, "成功",map);
			//可以设置登录失败的几种情况
		}
		catch (AuthenticationException e) {
			return ResultTools.result(404, "失败",map);
		}
	}
}
