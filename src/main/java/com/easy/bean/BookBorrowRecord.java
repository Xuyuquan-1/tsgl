package com.easy.bean;

import java.util.Date;

public class BookBorrowRecord {

    private int recordid;
    private int bookid;
    private int userid;
    private int adminid;
    private Date borrowdate;
    private Date returndate;
    private String status;

    private Book book;
    private User user;
    private Admin admin;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public int getRecordid() {
        return recordid;
    }

    public void setRecordid(int recordid) {
        this.recordid = recordid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public Date getBorrowdate() {
        return borrowdate;
    }

    public void setBorrowdate(Date borrowdate) {
        this.borrowdate = borrowdate;
    }

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
