package com.rain.controller;

import com.rain.utils.framework.result.Result;
import com.rain.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mail on 2017\11\10 0010.
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "user/registration")
    @ResponseBody
    public Result registration(){
        return userService.registration();
    }

    @RequestMapping(value = "user/cancel")
    @ResponseBody
    public Result cancel(){
        return userService.cancel();
    }
}
