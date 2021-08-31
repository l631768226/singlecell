package com.example.singlecell.model;

import java.util.List;

public class CtmViolinRst {

    private String imgStr;

    private String name;

    private String umapStr;

    private List<String> color;

    private List<String> legendData;

    private List<CtmUmapSeries> series;

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public String getUmapStr() {
        return umapStr;
    }

    public void setUmapStr(String umapStr) {
        this.umapStr = umapStr;
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

    public List<String> getLegendData() {
        return legendData;
    }

    public void setLegendData(List<String> legendData) {
        this.legendData = legendData;
    }

    public List<CtmUmapSeries> getSeries() {
        return series;
    }

    public void setSeries(List<CtmUmapSeries> series) {
        this.series = series;
    }
}
