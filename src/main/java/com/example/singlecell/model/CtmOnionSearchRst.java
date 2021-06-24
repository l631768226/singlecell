package com.example.singlecell.model;

import java.util.List;

public class CtmOnionSearchRst {

    private List<CtmSearchSecondRst> searchList;

    private List<CtmWindowDeg> dataList;

    private String upImgStr;

    private String downImgStr;

    public List<CtmSearchSecondRst> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<CtmSearchSecondRst> searchList) {
        this.searchList = searchList;
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
