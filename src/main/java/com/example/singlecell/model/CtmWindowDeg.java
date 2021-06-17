package com.example.singlecell.model;

public class CtmWindowDeg {

    private int id;

    private String dataset;

    private String tissue;

    private String celltype;

    private String gene;

    private String geneHtml;

    private String logfc;

    private String pvalue;

    public String getGeneHtml() {
        return geneHtml;
    }

    public void setGeneHtml(String geneHtml) {
        this.geneHtml = geneHtml;
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
