package com.easy.controller;


import com.easy.bean.Admin;
import com.easy.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    AdminDao dao;

    @RequestMapping("dologin")
    public String doLogin(Admin admin, HttpSession session) throws SQLException, IllegalAccessException, InstantiationException {
        //通过账号查询管理员信息
        List<Admin> list = dao.getAdminByName(admin.getUsername());
        //检查登录的信息和数据库的信息是否一致
        if(list.size()==1){
            Admin adminD = list.get(0);
            String password = admin.getPassword();
            String passwordD = adminD.getPassword();
            if(password!=null && passwordD.equals(password)){
                //如果一致就登录成功
                session.setAttribute("loginadmin",adminD);
                return "success";
            }
        }

        //否则登录失败
        return "fail";
    }

    //验证登录
    @RequestMapping("islogin")
    public Admin islogin(HttpSession session){
        Object obj = session.getAttribute("loginadmin");
        if(obj instanceof Admin){
            Admin result = (Admin) obj;
            return result;
        }
        return null;
    }

    //退出登录
    @RequestMapping("loginout")
    public String loginout(HttpSession session) {
        session.removeAttribute("loginadmin");
        return "success";
    }
}
