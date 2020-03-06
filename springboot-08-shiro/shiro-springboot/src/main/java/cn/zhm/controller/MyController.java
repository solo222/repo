package cn.zhm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("msg","hello,shiro");
        return "/index";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "user/add";
    }
    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/login";
    }

    @RequestMapping("/login")
    public String login(String username, String password,Boolean rememberMe, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = null;
        if(rememberMe == null){
            token = new UsernamePasswordToken(username, password);
        }else{
            token = new UsernamePasswordToken(username, password, rememberMe);
            token.setRememberMe(true);
        }
        try {
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名错误");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }
    @RequestMapping("/unauthorized")
    @ResponseBody
    public String unauthorized(){
        return "未授权页面";
    }

    @RequestMapping("/logout")
    public String logout(Model model){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
            model.addAttribute("msg","安全退出");
        }
        return "login";
    }

}
