package com.example.singlecell.model;

public class Deg {

    private int id;

    private String dataset;

    private String tissue;

    private String celltype;

    private String gene;

    private String logfc;

    private String pvalue;

    private String degId;

    public String getDegId() {
        return degId;
    }

    public void setDegId(String degId) {
        this.degId = degId;
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
}
