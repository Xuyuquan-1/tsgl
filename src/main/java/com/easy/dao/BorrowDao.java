package com.easy.dao;

import com.easy.bean.Book;
import com.easy.bean.BookBorrowRecord;
import com.easy.bean.User;
import com.easy.util.JdbcUtil;
import com.easy.util.Page;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BorrowDao {
    public int borrow(BookBorrowRecord borrow) {
        String sql="insert into bookborrowrecord (bookid,userid,adminid,borrowdate,status) values (?,?,?,now(),?)";
        int result= JdbcUtil.update(sql,borrow.getBookid(),borrow.getUserid(),borrow.getAdminid(),"borrowed");
        return result;
    }

    public int returned(BookBorrowRecord borrow) {
        String sql="update bookborrowrecord set returndate=now(),status='returned' where recordid=?";
        int result= JdbcUtil.update(sql,borrow.getRecordid());
        return result;
    }

    public List getRecord(User user, Book book, Page page) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select a.* from bookborrowrecord a left join user b on a.userid=b.userid left join book c on a.bookid=c.bookid";
        List params=new ArrayList();
        if(user!=null&&user.getName()!=null){
            sql=sql+" where b.name like ?";
            params.add("%"+user.getName()+"%");
        }
        if(book!=null&&book.getBooktitle()!=null){
            if(!sql.contains("where")){
                sql=sql+" where c.booktitle like ?";
            }
            sql=sql+" and c.booktitle like ?";
            params.add("%"+book.getBooktitle()+"%");
        }
        sql=sql+" order by status asc";
        if(page!=null&&page.getPage() > 0){
            sql=sql+" limit ?,?";
            params.add(page.getStart());
            params.add(page.getLimit());
        }
        ResultSet rs=JdbcUtil.query(sql,params.toArray());
        List<BookBorrowRecord> list=JdbcUtil.convertResultSetToList(rs,BookBorrowRecord.class);
        JdbcUtil.close(rs);
        return list;
    }

    public int getCount(User user, Book book) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select count(*) from bookborrowrecord a left join user b on a.userid=b.userid  left join book c on a.bookid=c.bookid";
        List params=new ArrayList();
        if(user!=null&&user.getName()!=null){
            sql=sql+" where b.name like ?";
            params.add("%"+user.getName()+"%");
        }
        if(book!=null&&book.getBooktitle()!=null){
            if(!sql.contains("where")){
                sql=sql+" where c.booktitle like ?";
            }
            sql=sql+" and c.booktitle like ?";
            params.add("%"+book.getBooktitle()+"%");
        }
        ResultSet rs=JdbcUtil.query(sql,params.toArray());
        rs.next();
        int count=rs.getInt(1);
        JdbcUtil.close(rs);
        return count;
    }
}
