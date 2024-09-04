package com.emazon.stock_api_service.domain.usecase;

import java.util.List;

public class PageResponse<T>{
    private Long totalPages;
    private Long totalElements;
    private Long pageSize;
    private Long currentPage;
    private List<T> content;
    public PageResponse(List<T> content,Long pageSize) {
        this.totalPages = Long.valueOf((content.size()/pageSize)+1);
        this.totalElements = Long.valueOf(content.size());
        this.pageSize = pageSize;
        this.currentPage = 1L;
        this.content = content;
    }
    public PageResponse(List<T> content,Long totalPages, Long totalElements, Long pageSize, Long currentPage) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }
    public List<T> getContent() {
        return content;
    }
    public Long getTotalPages() {
        return totalPages;
    }
    public Long getTotalElements() {
        return totalElements;
    }
    public Long getPageSize() {
        return pageSize;
    }
    public Long getCurrentPage() {
        return currentPage;
    }
}
