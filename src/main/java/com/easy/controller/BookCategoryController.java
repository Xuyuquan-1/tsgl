package com.easy.controller;

import com.easy.bean.BookCategory;
import com.easy.dao.BookCategoryDao;
import com.easy.util.Page;
import com.easy.util.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

/**
 * 提供对外开放的访问数据入口
 */
@RestController
@RequestMapping("category")
public class BookCategoryController {

    @Autowired
    BookCategoryDao dao;

    @RequestMapping("list")
    public RestResult getList(Page page,String checktext) throws SQLException, IllegalAccessException, InstantiationException {
        List list=dao.getList(page,checktext);
        int count=dao.getCount(checktext);
        RestResult result= new RestResult(count,list);
        return result;
    }

    @RequestMapping("add")
    public int add(BookCategory bookCategory){
        int result=dao.add(bookCategory);
        return result;
    }
    @RequestMapping("edit")
    public int edit(BookCategory bookCategory){
        int result=dao.edit(bookCategory);
        return result;
    }
    @RequestMapping("delete")
    public int delete(BookCategory bookCategory){
        int result=dao.delete(bookCategory);
        return result;
    }
}
