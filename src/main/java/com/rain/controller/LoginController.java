package com.rain.controller;

import com.rain.config.framework.result.Result;
import com.rain.controller.base.BaseController;
import com.rain.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by rain on 2017\11\10 0010.
 */
@Controller
public class LoginController extends BaseController {
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
