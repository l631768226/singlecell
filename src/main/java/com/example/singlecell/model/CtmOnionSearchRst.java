package com.example.singlecell.model;

import java.util.List;

public class CtmOnionSearchRst {

    private List<CtmSearchSecondRst> searchList;

    private List<CtmWindowDeg> dataList;

    private String upImgStr;

    private String downImgStr;

    private Double upMin;

    private Double upMax;

    private Double downMin;

    private Double downMax;

    private List<List<Object>> upDataList;

    private List<List<Object>> downDataList;

    public Double getUpMin() {
        return upMin;
    }

    public void setUpMin(Double upMin) {
        this.upMin = upMin;
    }

    public Double getUpMax() {
        return upMax;
    }

    public void setUpMax(Double upMax) {
        this.upMax = upMax;
    }

    public Double getDownMin() {
        return downMin;
    }

    public void setDownMin(Double downMin) {
        this.downMin = downMin;
    }

    public Double getDownMax() {
        return downMax;
    }

    public void setDownMax(Double downMax) {
        this.downMax = downMax;
    }

    public List<List<Object>> getUpDataList() {
        return upDataList;
    }

    public void setUpDataList(List<List<Object>> upDataList) {
        this.upDataList = upDataList;
    }

    public List<List<Object>> getDownDataList() {
        return downDataList;
    }

    public void setDownDataList(List<List<Object>> downDataList) {
        this.downDataList = downDataList;
    }

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
