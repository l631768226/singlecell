package com.example.singlecell.model;

import java.util.List;

public class CtmBrowseWindowRst {

    private List<CtmWindowDeg> dataList;

    private String upImgStr;

    private String downImgStr;

    private String dataset;

    private String celltype;

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public String getCelltype() {
        return celltype;
    }

    public void setCelltype(String celltype) {
        this.celltype = celltype;
    }

    public List<CtmWindowDeg> getDataList() {
        return dataList;
    }

    public void setDataList(List<CtmWindowDeg> dataList) {
        this.dataList = dataList;
    }

    public String getUpImgStr() {
        return upImgStr;
    }

    public void setUpImgStr(String upImgStr) {
        this.upImgStr = upImgStr;
    }

    public String getDownImgStr() {
        return downImgStr;
    }

    public void setDownImgStr(String downImgStr) {
        this.downImgStr = downImgStr;
    }
}
