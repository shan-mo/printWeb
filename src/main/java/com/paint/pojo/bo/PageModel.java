package com.paint.pojo.bo;

public class PageModel {
    private Integer start;
    private Integer pageSize = 5;
    private String createDate;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "PageModel{" +
                "start=" + start +
                ", pageSize=" + pageSize +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
