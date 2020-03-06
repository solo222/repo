package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
public class UserController {
    /**
     * 测试方法
     *
     * @return
     */
    @GetMapping("/hello")
    @ResponseBody

    public String hello() {
        System.out.println("UserController.hello()");
        return "ok";
    }

    /**
     * 测试Thymeleaf
     *
     * @param model
     * @return
     */
    @GetMapping("/testThymeleaf")
    public String testThymeleaf(Model model) {
        //把数据存入model
        model.addAttribute("name", "程序员");
        //返回test.html
        return "test";
    }

}
