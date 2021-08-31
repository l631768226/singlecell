package com.example.singlecell.model;

import java.util.List;

public class CtmUmapSeries {

    private List<VolcanoValueData> data;

    private String type = "scatter";

    private int symbolSize = 6;

    private String name;

    private UmapTooltip encode = new UmapTooltip();

    public List<VolcanoValueData> getData() {
        return data;
    }

    public void setData(List<VolcanoValueData> data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSymbolSize() {
        return symbolSize;
    }

    public void setSymbolSize(int symbolSize) {
        this.symbolSize = symbolSize;
    }

    public UmapTooltip getEncode() {
        return encode;
    }

    public void setEncode(UmapTooltip encode) {
        this.encode = encode;
    }
}
