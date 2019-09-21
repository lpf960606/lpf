package com.kgc.lpf.shiro.dao;

import com.kgc.lpf.shiro.bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

    public User selectByName(String name);

    /**
     * 注册
     *
     * @return
     */
    public boolean adduser(@Param("uname") String uname, @Param("upwd") String upwd);

    public boolean addur(@Param("uname") String uname, @Param("upwd") String upwd);


}
