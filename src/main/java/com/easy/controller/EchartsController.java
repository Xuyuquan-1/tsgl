package com.easy.controller;


import com.easy.dao.EchartsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
@RequestMapping("echarts")
public class EchartsController {

    @Autowired
    EchartsDao echartsDao;
    @RequestMapping("echartsa")
    public List echartsA() throws SQLException, IllegalAccessException, InstantiationException {
        List<HashMap> list=echartsDao.echartA();
        List result = new ArrayList();
        List listx = new ArrayList();
        List listy = new ArrayList();
        for(HashMap item:list){
            item.keySet();
        }
    }
}
