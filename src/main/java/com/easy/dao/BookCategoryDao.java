package com.easy.dao;

import com.easy.bean.Book;
import com.easy.bean.BookCategory;
import com.easy.util.JdbcUtil;
import com.easy.util.Page;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookCategoryDao {

    //查询数据

    //查询集合
    public List<BookCategory> getList(Page page, String checktext) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from bookcategory ";
        List params = new ArrayList();
        if(checktext != null)
        {
            sql = sql+"where categoryname like ?";
            params.add("%"+checktext+"%");
        }
        if(page!=null){
            sql = sql+" limit ?,? ";
            params.add(page.getStart());
            params.add(page.getLimit());
        }
        ResultSet rs = JdbcUtil.query(sql,params.toArray());
        List<BookCategory> list = JdbcUtil.convertResultSetToList(rs, BookCategory.class);
        JdbcUtil.close(rs);
        return list;


    }
    //查询一条数据
    public BookCategory getCategoryById(int categoryid) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from bookcategory where id=?";
        ResultSet rs = JdbcUtil.query(sql,categoryid);
        //将ResultSet转成BookCategory对象
        List<BookCategory> list=JdbcUtil.convertResultSetToList(rs, BookCategory.class);
        //关闭资源
        JdbcUtil.close(rs);
        return list.get(0);
    }

    //查询数量
    public int getCount(String checktest) throws SQLException {
        String sql = "select count(*) from bookcategory ";
        if(checktest != null){
            sql = sql+"where categoryname like ?";
            ResultSet rs = JdbcUtil.query(sql,"%"+checktest+"%");
            rs.next();
            int count = rs.getInt(0);
            JdbcUtil.close(rs);
            return count;
        }
        ResultSet rs = JdbcUtil.query(sql);
        rs.next();
        int count = rs.getInt(0);
        JdbcUtil.close(rs);
        return count;
    }

    //新增数据
    public int add(BookCategory bookCategory)
    {
        String sql = "insert into bookcategory values(?)";
        int result = JdbcUtil.update(sql,bookCategory.getCategroyname());
        return result;
    }

    //编辑数据

    public int edit(BookCategory bookCategory)
    {
        String sql = "update bookcategory set categoryname =? where categoryid=?";
        int result = JdbcUtil.update(sql,bookCategory.getCategroyname(),bookCategory.getCategoryid());
        return result;

    }
    //删除数据  此方法返回删除数据的行数
    public int delete(BookCategory bookCategory){
        //定义
        String sql = "delete from bookcategory where bookcategoryid=?";
        //执行
        int result = JdbcUtil.update(sql,bookCategory.getCategoryid());
        return result;

    }

}
