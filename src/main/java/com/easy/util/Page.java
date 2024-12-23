package com.easy.util;

public class Page {
    private int page;
    private int start;
    private int limit;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return (page-1)*limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
