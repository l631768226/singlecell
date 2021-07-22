package com.example.singlecell.model;

import java.util.List;

public class CtmVolcanoSeries {

    private List<VolcanoValueData> data;

    private String type = "scatter";

    private int symbolSize = 6;

    private String name;

    private VolcanoToolTip encode = new VolcanoToolTip();

    private MarkLine markLine;

    public List<VolcanoValueData> getData() {
        return data;
    }

    public void setData(List<VolcanoValueData> data) {
        this.data = data;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VolcanoToolTip getEncode() {
        return encode;
    }

    public void setEncode(VolcanoToolTip encode) {
        this.encode = encode;
    }

    public MarkLine getMarkLine() {
        return markLine;
    }

    public void setMarkLine(MarkLine markLine) {
        this.markLine = markLine;
    }
}
