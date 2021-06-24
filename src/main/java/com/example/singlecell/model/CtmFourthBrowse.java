package com.example.singlecell.model;

import java.util.List;

public class CtmFourthBrowse {

    private List<FourthData> dataList;

    private List<ListData> dropList;


    private String dataset;

    private String tissue;

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public String getTissue() {
        return tissue;
    }

    public void setTissue(String tissue) {
        this.tissue = tissue;
    }

    public List<ListData> getDropList() {
        return dropList;
    }

    public void setDropList(List<ListData> dropList) {
        this.dropList = dropList;
    }

    public List<FourthData> getDataList() {
        return dataList;
    }

    public void setDataList(List<FourthData> dataList) {
        this.dataList = dataList;
    }
}
