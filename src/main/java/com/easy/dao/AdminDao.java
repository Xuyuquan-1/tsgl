package com.easy.dao;

import com.easy.bean.Admin;
import com.easy.util.JdbcUtil;
import jdk.nashorn.internal.runtime.ListAdapter;
import org.springframework.stereotype.Repository;

import javax.sql.rowset.JdbcRowSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AdminDao {
    public List<Admin> getAdminByName(String username) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from admin where username=? ";
        ResultSet rs = JdbcUtil.query(sql,username);
        List<Admin> list = JdbcUtil.convertResultSetToList(rs,Admin.class);
        return list;
    }
}
