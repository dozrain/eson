package com.rain.controller;

import com.rain.service.DemoService;
import com.rain.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Created by rain on 2017\11\10 0010.
 */
@Controller
public class LoginController {
    @Autowired
    private DemoService demoService;
    /**
     * 登录接口
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "login")
    public Result login(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String userAccount = request.getParameter("username");
        String userPassword = request.getParameter("userPassword");

        return new Result();
    }
}
