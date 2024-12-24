package com.easy.dao;

import com.easy.bean.Book;
import com.easy.util.JdbcUtil;
import com.easy.util.Page;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDao {
    public List<Book> getList(Page page,String checktext) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select * from book ";
        List params=new ArrayList();
        if(checktext!=null){
            sql+=" where booktitle like ?";
            params.add("%"+checktext+"%");
        }
        if(page!=null){
            sql+=" limit ?,? ";
            params.add(page.getStart());
            params.add(page.getLimit());
        }
        //执行sql语句
        ResultSet rs= JdbcUtil.query(sql,params.toArray());
        //将结果集中的数据转换成对应类型的对象
        List<Book> list = JdbcUtil.convertResultSetToList(rs, Book.class);
        //关闭资源
        JdbcUtil.close(rs);
        return list;
    }

    //查询表中一共有多少数据
    public int getCount(String checktext) throws SQLException {
        String sql="select count(*) from book ";
        if(checktext!=null){
            sql+=" where booktitle like ?";
            ResultSet rs=JdbcUtil.query(sql,"%"+checktext+"%");
            rs.next();
            int result = rs.getInt(1);
            return result;
        }
        ResultSet rs=JdbcUtil.query(sql);
        rs.next();
        int result = rs.getInt(1);
        return result;
    }

    //添加
    public int add(Book book){
        String sql="insert into book (booktitle,author,publisheddate,categoryid,pages,price) values(?,?,?,?,?,?)";
        int result=JdbcUtil.update(sql,book.getBooktitle(),book.getAuthor(),book.getPublisheddate(),book.getCategoryid(),book.getPages(),book.getPrice());
        return result;

    }

    //编辑
    public int edit(Book book){
        String sql="update book set booktitle=?,author=?,publisheddate=?,categoryid=?,pages=?,price=? where bookid=?";
        int result=JdbcUtil.update(sql,book.getBooktitle(),book.getAuthor(),book.getPublisheddate(),book.getCategoryid(),book.getPages(),book.getPrice(),book.getBookid());
        return result;
    }

    //删除
    public int delete(Book book){
        String sql="delete from book where bookid=?";
        int result=JdbcUtil.update(sql,book.getBookid());
        return result;
    }
}
