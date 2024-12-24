package com.easy.dao;

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

    //1.查询集合
    public List<BookCategory> getList(Page page, String checktext) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select * from bookcategory ";
        List params=new ArrayList();
        if(checktext!=null){
            sql=sql+" where categoryname like ?";
            params.add("%"+checktext+"%");
        }
        if(page!=null){
            sql=sql+" limit ?,?";
            params.add(page.getStart());
            params.add(page.getLimit());
        }
        ResultSet rs=JdbcUtil.query(sql,params.toArray());
        List<BookCategory> list=JdbcUtil.convertResultSetToList(rs,BookCategory.class);
        JdbcUtil.close(rs);
        return list;
    }
    //2.查询一条数据
    public BookCategory getCategoryById(int categoryid) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select * from bookcategory where categoryid=?";
        ResultSet rs=JdbcUtil.query(sql,categoryid);
        //将ResultSet转成bookcategory类的对象
        List<BookCategory> list = JdbcUtil.convertResultSetToList(rs, BookCategory.class);
        //关闭资源
        JdbcUtil.close(rs);
        return list.get(0);
    }
    //查询数量
    public int getCount(String checktext) throws SQLException {
        String sql="select count(*) from bookcategory ";
        if(checktext!=null){
            sql=sql+" where categoryname like?";
            ResultSet rs = JdbcUtil.query(sql, "%" + checktext + "%");
            rs.next();
            int count=rs.getInt(1);
            return count;
        }
        ResultSet rs=JdbcUtil.query(sql);
        rs.next();
        int count = rs.getInt(1);
        JdbcUtil.close(rs);
        return count;
    }

    //新增数据
    public int add(BookCategory bookCategory){
        String sql="insert into bookcategory (categoryname) values(?)";
        int result=JdbcUtil.update(sql,bookCategory.getCategoryname());
        return result;
    }

    //编辑数据
    public int edit(BookCategory bookCategory){
        String sql="update bookcategory set categoryname=? where categoryid=?";
        int result=JdbcUtil.update(sql,bookCategory.getCategoryname(),bookCategory.getCategoryid());
        return result;
    }

    //删除数据  此方法返回删除数据的行数
    public int delete(BookCategory bookCategory){
        //定义要执行的SQL语句
        String sql="delete from bookcategory where categoryid=?";
        //执行SQL语句
        int result= JdbcUtil.update(sql,bookCategory.getCategoryid());

        return result;
    }
}
