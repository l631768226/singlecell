package com.example.singlecell.model;

import java.util.List;

public class CtmSearchDetailRst {

    private int id;

    private String dataset;

    private String tissue;

    private String celltype;

    private String gene;

    private String logfc;

    private String pvalue;

    private String boxStr;

    private String umapStr;

    private String heatmapStr;

    private String volcaStr;

    private String violinStr;

    private String hasPic;

    private String name;

    private String imgStr;

    private List<String> color;

    private List<String> legendData;

    private List<CtmVolcanoSeries> series;

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

    public String getHasPic() {
        return hasPic;
    }

    public void setHasPic(String hasImg) {
        this.hasPic = hasImg;
    }

    public String getViolinStr() {
        return violinStr;
    }

    public void setViolinStr(String violinStr) {
        this.violinStr = violinStr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getCelltype() {
        return celltype;
    }

    public void setCelltype(String celltype) {
        this.celltype = celltype;
    }

    public String getGene() {
        return gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    public String getLogfc() {
        return logfc;
    }

    public void setLogfc(String logfc) {
        this.logfc = logfc;
    }

    public String getPvalue() {
        return pvalue;
    }

    public void setPvalue(String pvalue) {
        this.pvalue = pvalue;
    }

    public String getBoxStr() {
        return boxStr;
    }

    public void setBoxStr(String boxStr) {
        this.boxStr = boxStr;
    }

    public String getUmapStr() {
        return umapStr;
    }

    public void setUmapStr(String umapStr) {
        this.umapStr = umapStr;
    }

    public String getHeatmapStr() {
        return heatmapStr;
    }

    public void setHeatmapStr(String heatmapStr) {
        this.heatmapStr = heatmapStr;
    }

    public String getVolcaStr() {
        return volcaStr;
    }

    public void setVolcaStr(String volcaStr) {
        this.volcaStr = volcaStr;
    }
}
