package com.kgc.lpf.shiro.config;

import com.kgc.lpf.shiro.bean.Perssion;
import com.kgc.lpf.shiro.bean.Role;
import com.kgc.lpf.shiro.bean.User;

import com.kgc.lpf.shiro.service.UserService;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class Myrealm extends AuthorizingRealm {

    @Autowired
    UserService us;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //创建保存权限和角色信息的对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取成功登录的用户信息
        User user = (User) principalCollection.getPrimaryPrincipal();
        //遍历获取角色，权限
        for (Role r : user.getRole()) {
            //将角色名添加到info
            info.addRole(r.getRname());
            for (Perssion p : r.getPerssion()) {
                //遍历获取所有的权限，将权限名添加到info
                info.addStringPermission(p.getPname());
            }
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //非空判断，不建议放后台
        if (token.getPrincipal() == null) {
            return null;
        }
        //从token获取用户登录输入的用户名
        String uname = token.getPrincipal().toString();
        //调用第一步完成的service实现数据库查询
        User user = us.selectByName(uname);
        //用户名不存在
        if (user == null) {
            return null;
        } else {
            //用户名存在 将查到的用户对象，密码，当前realm的name传递给info
            //如果不需要权限验证，则传递的第一个参数可以为用户名
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getUpwd(), getName());
            return info;
        }
    }
}
