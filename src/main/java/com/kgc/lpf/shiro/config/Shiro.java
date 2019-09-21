package com.kgc.lpf.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class Shiro {


    //过滤器
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager manager) {
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(manager);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/static/**", "anon");//anon验证 开发白名单资源
        map.put("/layui/**", "anon");
        map.put("/login", "anon");
        map.put("/", "anon");
        map.put("/toin", "anon");
        map.put("/test", "anon");
        map.put("/add", "anon");
        map.put("/**", "authc");//authc登录验证
        //设置登录链接
        filter.setLoginUrl("/tologin");
        //设置登录成功的跳转
        filter.setSuccessUrl("/index");
        //将映射设置到过滤器
        filter.setFilterChainDefinitionMap(map);

        return filter;
    }

    //创建自定义的realm
    @Bean
    public Myrealm myrealm() {
        Myrealm myrealm = new Myrealm();
        return myrealm;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myrealm());
        return securityManager;
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

}
