package com.cpt202.xunwu.bean;

import java.util.List;

public class ComPage<T> {

    private Integer page;
    private Integer size;
    private Integer totalPages;
    private Long total;
    private List<T> pageContent;

    public ComPage(Integer page, Integer size, Integer totalPages, Long total, List<T> pageContent) {
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.total = total;
        this.pageContent = pageContent;
    }
    
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }
    public Integer getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
    public List<T> getPageContent() {
        return pageContent;
    }
    public void setPageContent(List<T> pageContent) {
        this.pageContent = pageContent;
    }
    
}

