package com.easy.dao;


import com.easy.util.JdbcUtil;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
public class EchartsDao {
    public List echartA() throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select c.categoryname,count(a.bookid) as jysl from bookborrowrecord as a "+
                "right join book as b on a.bookid = b.bookid "+
                "right join bookcategory as c on b categoryid=c.categoryid"+
                " group by c.categoryname ";
        ResultSet rs = JdbcUtil.query(sql);
        List<HashMap> list = JdbcUtil.convertToHashMap(rs);
        JdbcUtil.close(rs);
        return list;

    }
}


