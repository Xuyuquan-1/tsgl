package com.easy.bean;

/**
 * 用来存储数据库中查询的图书分类信息
 */
public class BookCategory {
    private int categoryid;
    private String categoryname;



    public String getCategroyname() {

        return categoryname;
    }

    public void setCategroyname(String categroyname) {

        this.categoryname = categroyname;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

}
