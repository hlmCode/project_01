package com.itheima.pojo;

import java.util.List;

public class PageBean<T> {
    private List<T> rows;
    private int totalCount;

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
