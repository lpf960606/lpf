package com.kgc.lpf.shiro.service.impl;

import com.kgc.lpf.shiro.bean.*;
import com.kgc.lpf.shiro.dao.UserDao;
import com.kgc.lpf.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserServiceimpl implements UserService {

    @Autowired
    UserDao ud;

    @Override
    public User selectByName(String uname) {
        return ud.selectByName(uname);
    }

    @Override
    public boolean addinfo(String uname, String upwd) {
        boolean flag = false;
        if (ud.adduser(uname, upwd) == true) {
            if (ud.addur(uname, upwd)==true) {
                flag = true;
            } else {
                flag = false;
            }
        } else {
            flag = true;
            System.out.println("ddd");
        }
        return flag;
    }


}
