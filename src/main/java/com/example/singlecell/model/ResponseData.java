package com.example.singlecell.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.PageInfo;

@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class ResponseData<T> {

    private ReturnStatus status;

    private Object extInfo;

    private T resultSet;

    private Paging paging;

    public ReturnStatus getStatus() {
        return status;
    }

    public void setStatus(ReturnStatus status) {
        this.status = status;
    }

    public Object getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Object extInfo) {
        this.extInfo = extInfo;
    }

    public T getResultSet() {
        return resultSet;
    }

    public void setResultSet(T resultSet) {
        this.resultSet = resultSet;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging){
        this.paging = paging;
    }

    public void setPaging(PageInfo<?> pageInfo) {
        if(pageInfo != null){
            this.paging = new Paging();
            this.paging.setPageNum(pageInfo.getPageNum());
            this.paging.setPages(pageInfo.getPages());
            this.paging.setPageSize(pageInfo.getPageSize());
            this.paging.setTotal(pageInfo.getTotal());
            return;
        }
    }
}
