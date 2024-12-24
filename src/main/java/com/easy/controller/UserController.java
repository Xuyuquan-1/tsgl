package com.easy.controller;

import com.easy.bean.Book;
import com.easy.bean.BookCategory;
import com.easy.bean.User;
import com.easy.dao.BookCategoryDao;
import com.easy.dao.BookDao;
import com.easy.dao.UserDao;
import com.easy.util.Page;
import com.easy.util.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserDao userDao;

//    BookCategoryDao categoryDao;

    @RequestMapping("list")
    public RestResult getList(Page page,String checktext) throws SQLException, IllegalAccessException, InstantiationException {
        List<User> list=userDao.getList(page,checktext);
        //将分类id取出通过id查询分类对象将分类的对象设置到图书对象中
//        for(User item:list){
//            int categoryid=item.getUserid();
//            BookCategory category=categoryDao.getCategoryById(categoryid);
//            item.setCategory(category);
//        }
        int count=userDao.getCount(checktext);
        RestResult result=new RestResult(count,list);
        return result;
    }

    @RequestMapping("add")
    public int add(User user){
        int result=userDao.add(user);
        return result;
    }

    @RequestMapping("edit")
    public int edit(User user){
        int result = userDao.edit(user);
        return result;
    }

    @RequestMapping("delete")
    public int delete(User user){
        int result=userDao.delete(user);
        return result;
    }

}
