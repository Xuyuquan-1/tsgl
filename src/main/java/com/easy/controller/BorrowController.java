package com.easy.controller;


import com.easy.bean.Admin;
import com.easy.bean.BookBorrowRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("borrow")
public class BorrowController {

    @Autowired
    BorrowDao borrowDao;

    @RequestMapping("borrow")
    public int borrow(BookBorrowRecord borrow, HttpSession session){
        //获取到登陆管理员的id
        Object obj = session.getAttribute("loginadmin");
        if(obj instanceof Admin){
            Admin admin=(Admin) obj;
            int adminid = admin.getAdminid();
            borrow.setAdminid(adminid);
        }
        return borrowDao.borrow(borrow);
    }
}
