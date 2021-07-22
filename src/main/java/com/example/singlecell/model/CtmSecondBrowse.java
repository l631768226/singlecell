package com.example.singlecell.model;

import java.util.List;

public class CtmSecondBrowse {

    private String umapStr;

    private List<String> legendData;

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
