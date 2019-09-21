package com.kgc.lpf.shiro.service;

import com.kgc.lpf.shiro.bean.*;

public interface UserService {
    public User selectByName(String name);

    public boolean addinfo(String uname, String upwd);


}
