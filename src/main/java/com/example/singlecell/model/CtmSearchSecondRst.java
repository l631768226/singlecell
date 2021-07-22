package com.example.singlecell.model;

import java.util.List;

public class CtmSearchSecondRst {

    private String name;

    private String imgStr;

    private String dataset;

    private String celltype;

    private List<String> color;

    private List<String> legendData;

    private List<CtmVolcanoSeries> series;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgStr() {
        return imgStr;
    }

    public void setImgStr(String imgStr) {
        this.imgStr = imgStr;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public List<String> getLegendData() {
        return legendData;
    }

    public void setLegendData(List<String> legendData) {
        this.legendData = legendData;
    }

    public List<CtmVolcanoSeries> getSeries() {
        return series;
    }

    public void setSeries(List<CtmVolcanoSeries> series) {
        this.series = series;
    }
}
