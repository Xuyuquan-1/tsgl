package com.easy.dao;

import com.easy.bean.Admin;
import com.easy.util.JdbcUtil;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AdminDao {
    public List<Admin> getAdminByName(String username) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from admin where username=?";
        ResultSet rs = JdbcUtil.query(sql, username);
        List<Admin> list = JdbcUtil.convertResultSetToList(rs, Admin.class);
        JdbcUtil.close(rs);
        return list;
    }

    public Admin getAdminByadminId(int adminid) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from admin where adminid=?";
        ResultSet rs = JdbcUtil.query(sql, adminid);
        List<Admin> list = JdbcUtil.convertResultSetToList(rs, Admin.class);
        Admin result = list.get(0);
        JdbcUtil.close(rs);
        return result;
    }
}