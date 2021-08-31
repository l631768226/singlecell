package com.example.singlecell.model;

import java.util.LinkedList;
import java.util.List;

public class CtmThirdBrowse {

    private LinkedList<String> sampleList;

    private List<ThiredData> dataList;

    private List<String> color;

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public LinkedList<String> getSampleList() {
        return sampleList;
    }

    public void setSampleList(LinkedList<String> sampleList) {
        this.sampleList = sampleList;
    }

    public List<ThiredData> getDataList() {
        return dataList;
    }

    public void setDataList(List<ThiredData> dataList) {
        this.dataList = dataList;
    }
}
