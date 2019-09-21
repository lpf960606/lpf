package com.kgc.lpf.shiro.controller;

import com.kgc.lpf.shiro.bean.*;
import com.kgc.lpf.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService us;

    @RequestMapping("")
    public String go() {
        return "index";
    }

    @RequestMapping("tologin")
    public String go1() {
        return "login";
    }

    @RequestMapping("toin")
    public String in() {
        return "register";
    }

    @RequestMapping("login")
    public String lo(String uname, String upwd) {
        UsernamePasswordToken token = new UsernamePasswordToken(uname, upwd, true);
        Subject subject = SecurityUtils.getSubject(); //获取shiro对象
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return "redirect:/tologin"; //用户名不存在
        } catch (IncorrectCredentialsException e) {
            return "redirect:/tologin";//密码错误
        }
        return "index";

    }

    @RequestMapping("add")
    public ModelAndView add(String uname, String upwd) {
        ModelAndView m = new ModelAndView();
        if (us.selectByName(uname) != null) {
            m.setViewName("register");
        } else {
            boolean flag = us.addinfo(uname, upwd);
            if (flag == true) {
                m.setViewName("login");
            } else {
                m.setViewName("register");
            }
        }
        return m;
    }

    @RequestMapping("test")
    @ResponseBody
    public Page<Perssion> se(Integer page, Integer limit) {
        Page<Perssion> p = new Page<Perssion>();
        Perssion per = new Perssion();
        per.setPid(1);
        per.setPname("aaaa");
        List<Perssion> list = new ArrayList<>();
        list.add(per);
        list.add(per);
        list.add(per);
        p.setCount(100);
        p.setMsg("success");
        p.setData(list);
        return p;
    }

}
