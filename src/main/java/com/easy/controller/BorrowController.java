package com.easy.controller;

import com.easy.bean.Admin;
import com.easy.bean.Book;
import com.easy.bean.BookBorrowRecord;
import com.easy.bean.User;
import com.easy.dao.AdminDao;
import com.easy.dao.BookDao;
import com.easy.dao.BorrowDao;
import com.easy.dao.UserDao;
import com.easy.util.Page;
import com.easy.util.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("borrow")
public class BorrowController {

    @Autowired
    BorrowDao borrowDao;

    @Autowired
    UserDao userDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    AdminDao adminDao;

    @RequestMapping("borrow")
    public int borrow(BookBorrowRecord borrow, HttpSession session) {
        Object obj = session.getAttribute("loginadmin");
        if (obj instanceof Admin) {
            Admin admin = (Admin) obj;
            int adminid = admin.getAdminid();
            borrow.setAdminid(adminid);
        }
        return borrowDao.borrow(borrow);
    }

    @RequestMapping("returned")
    public int returned(BookBorrowRecord borrow) {
        return borrowDao.returned(borrow);
    }

    @RequestMapping("list")
    public RestResult getList(User user, Book book, Page page) throws SQLException, IllegalAccessException, InstantiationException {
        List<BookBorrowRecord> list = borrowDao.getRecord(user, book, page);
        for (BookBorrowRecord item:list) {
            int userid = item.getUserid();
            int bookid = item.getBookid();
            int adminid = item.getAdminid();
            //查询用户信息
            item.setUser(userDao.getUserByUserId(userid));
            //查询图书信息
            item.setBook(bookDao.getBookBybookId(bookid));
            //查询管理员信息
            item.setAdmin(adminDao.getAdminByadminId(adminid));
        }
        int count = borrowDao.getCount(user, book);
        RestResult result = new RestResult(count, list);
        return result;
    }
}