package com.easy.bean;

/**
 * 用来存储数据库中查询的图书分类信息
 */
public class BookCategory {
    private int categoryid;
    private String categoryname;

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
}
