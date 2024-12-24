package com.easy.dao;

import com.easy.bean.Book;
import com.easy.bean.User;
import com.easy.util.JdbcUtil;
import com.easy.util.Page;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    public List<User> getList(Page page,String checktext) throws SQLException, IllegalAccessException, InstantiationException {
        String sql="select * from user ";
        List params=new ArrayList();
        if(checktext!=null){
            sql+=" where name like ?";
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
        List<User> list = JdbcUtil.convertResultSetToList(rs, User.class);
        //关闭资源
        JdbcUtil.close(rs);
        return list;
    }

    //查询表中一共有多少数据
    public int getCount(String checktext) throws SQLException {
        String sql="select count(*) from user ";
        if(checktext!=null){
            sql+=" where name like ?";
            ResultSet rs=JdbcUtil.query(sql,"%"+checktext+"%");
            rs.next();
            int result = rs.getInt(1);
            return result;
        }
        ResultSet rs=JdbcUtil.query(sql);
        rs.next();
        int result = rs.getInt(1);
        JdbcUtil.close(rs);
        return result;
    }

    //添加
    public int add(User user){
        String sql=" insert into user (name, email, phone) values(?,?,?)";
        int result=JdbcUtil.update(sql,user.getName(),user.getEmail(),user.getPhone());
        return result;

    }

    //编辑
    public int edit(User user){
        String sql=" update user set name=?,email=?,phone=? where userid=?";
        int result=JdbcUtil.update(sql,user.getName(),user.getEmail(),user.getPhone(),user.getUserid());
        return result;
    }

    //删除
    public int delete(User user){
        String sql="delete from user where userid=?";
        int result=JdbcUtil.update(sql,user.getUserid());
        return result;
    }
}
