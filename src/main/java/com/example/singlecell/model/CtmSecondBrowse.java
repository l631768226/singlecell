package com.example.singlecell.model;

import java.util.List;
import java.util.Map;

public class CtmSecondBrowse {

    private String umapStr;

    private List<String> legendData;

    private List<String> color;

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    //    private Map<String, Boolean> selected;

    private List<CtmUmapSeries> series;

    public String getUmapStr() {
        return umapStr;
    }

    public void setUmapStr(String umapStr) {
        this.umapStr = umapStr;
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
