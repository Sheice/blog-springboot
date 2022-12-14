package com.sheice.blog.dtos;

import java.util.List;

public class PublicationResponse {

    private List<PublicationDTO> content;
    private int pageNum;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private boolean last;

    // constructors

    public PublicationResponse() {
    }


    //getters and setters


    public List<PublicationDTO> getContent() {
        return content;
    }

    public void setContent(List<PublicationDTO> content) {
        this.content = content;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}
