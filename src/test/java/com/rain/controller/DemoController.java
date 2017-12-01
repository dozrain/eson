package com.rain.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017\11\10 0010.
 */
@Controller
@EnableAutoConfiguration
public class DemoController {

    //test
    @RequestMapping("/")
    @ResponseBody
    public String priceList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "hello world";

    };

    public static void main(String[] args) {

        System.out.println("123");
        System.out.println("456");
    }
}
