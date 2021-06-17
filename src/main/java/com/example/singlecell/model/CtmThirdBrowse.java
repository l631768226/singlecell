package com.example.singlecell.model;

import java.util.LinkedList;
import java.util.List;

public class CtmThirdBrowse {

    private LinkedList<String> sampleList;

    private List<ThiredData> dataList;

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
