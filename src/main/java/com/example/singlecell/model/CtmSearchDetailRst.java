package com.example.singlecell.model;

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
