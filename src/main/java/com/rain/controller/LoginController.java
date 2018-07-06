package com.rain.controller;

import com.rain.utils.framework.result.Result;
import com.rain.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by mail on 2017\11\10 0010.
 */
@Controller
public class LoginController{
    @Resource
    private LoginService loginService;
    /**
     * 登录接口
     * @param
     * @return
     */
    @RequestMapping(value = "loginGo")
    @ResponseBody
    public Result loginGo(){
       return loginService.loginGo();
    };
    /**
     * 退出登录接口
     * @param
     * @return
     */
    @RequestMapping(value = "loginOut")
    @ResponseBody
    public Result loginOut(){
     return loginService.loginOut();
    }

}
