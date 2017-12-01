package com.rain.controller;

import com.rain.dao.entity.User;
import com.rain.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017\11\10 0010.
 */
@Controller
@RequestMapping(value = "/user")
public class DemoController {
    @Autowired
    private DemoService demoService;

    //test
    @RequestMapping("/")
    @ResponseBody
    public String demoController(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "hello world";
    };

    @RequestMapping(value = "/show")
    @ResponseBody
    public String show(@RequestParam(value = "name")String name){
        User user = demoService.findUserByName(name);
        if(null != user)
            return user.getId()+"/"+user.getName()+"/"+user.getPassword();
        else return "null";
    }
}
