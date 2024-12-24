package com.easy.controller;

import com.easy.bean.Book;
import com.easy.bean.BookCategory;
import com.easy.dao.BookCategoryDao;
import com.easy.dao.BookDao;
import com.easy.util.Page;
import com.easy.util.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookDao bookDao;
    @Autowired
    BookCategoryDao categoryDao;

    @RequestMapping("list")
    public RestResult getList(Page page,String checktext) throws SQLException, IllegalAccessException, InstantiationException {
        List<Book> list=bookDao.getList(page,checktext);
        //将分类id取出通过id查询分类对象将分类的对象设置到图书对象中
        for(Book item:list){
            int categoryid=item.getCategoryid();
            BookCategory category=categoryDao.getCategoryById(categoryid);
            item.setCategory(category);
        }
        int count=bookDao.getCount(checktext);
        RestResult result=new RestResult(count,list);
        return result;
    }

    @RequestMapping("add")
    public int add(Book book){
        int result=bookDao.add(book);
        return result;
    }

    @RequestMapping("edit")
    public int edit(Book book){
        int result=bookDao.edit(book);
        return result;
    }

    @RequestMapping("delete")
    public int delete(Book book){
        int result=bookDao.delete(book);
        return result;
    }

}
